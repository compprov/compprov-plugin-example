# compprov-plugin-example

An example **plugin** for [compprov-analytics](https://github.com/compprov/compprov-analytics),
showing how to extend it without modifying its code: a domain-specific type wrapper
(`ComputationEnvironment` customization) and a custom LLM chat model, both discovered at
runtime via `java.util.ServiceLoader`.

If you're looking for the core provenance-tracking framework itself, see
[compprov-core](https://github.com/compprov/compprov-core).

---

## Contents

- [How plugins work](#how-plugins-work)
- [What this plugin provides](#what-this-plugin-provides)
- [Building](#building)
- [Using the plugin](#using-the-plugin)
- [Test snapshots](#test-snapshots)
- [Sample analysis run](#sample-analysis-run)
- [Project layout](#project-layout)
- [License](#license)

---

## How plugins work

`compprov-analytics` accepts one or more `--plugin=<path-to-jar>` command-line arguments. For
each plugin jar, it opens a `URLClassLoader` over the jar and asks `ServiceLoader` for two kinds
of providers declared under `META-INF/services`:

| Service interface | Purpose |
|---|---|
| `io.compprov.core.EnvironmentCustomizer` | Given the shared `ComputationEnvironment`, register additional type wrappers, serializers, etc. |
| `dev.langchain4j.model.chat.ChatModel` | Supplies the LLM used for prompt-based analysis of a CPG. |

Any provider found is applied automatically — no other wiring is required. `compprov-core` and
`langchain4j-open-ai` are declared with `provided` scope in [`pom.xml`](pom.xml) because they're
already on the classpath `compprov-analytics` loads plugins with, so this jar doesn't bundle
them. `langchain4j-anthropic` is *not* provided by the host, so it's declared as a normal
`compile` dependency and shaded into this jar at build time (see [Building](#building)).

## What this plugin provides

### NAV (Net Asset Value) domain type wrappers

`io.compprov.examples.nav` is a small, framework-agnostic domain model for a multi-currency
portfolio, including a simple vanilla-options position type:

- [`Amount`](src/main/java/io/compprov/examples/nav/model/Amount.java) — a `Currency` plus a
  `BigDecimal`, scaled to that currency's decimal precision and always rounded down. Truncation
  is deliberate: when paying out of a fixed balance, rounding a debit up could subtract more than
  is actually available and drive the balance negative, so every `Amount` operation truncates
  rather than rounds to guarantee that never happens.
- [`Rate`](src/main/java/io/compprov/examples/nav/model/Rate.java) — an exchange rate between
  two currencies, used by `Amount.convert(Rate)`.
- [`Currency`](src/main/java/io/compprov/examples/nav/model/Currency.java) — `BTC`, `ETH`,
  `USDC`, `USDT`, `USD`, `WSTETH`, each with its own decimal precision.
- [`OptionPosition`](src/main/java/io/compprov/examples/nav/model/OptionPosition.java) — a
  vanilla call/put position (see [`OptionType`](src/main/java/io/compprov/examples/nav/model/OptionType.java))
  with a strike `Rate` and notional `Amount`, computing its own payout at a given settlement price.

`io.compprov.examples.nav.wrapped` integrates these types with compprov-core **without changing
them**, following the three-step "custom type wrapper" pattern (see the "Extending with custom
type wrappers" section of the compprov-core README for the full walkthrough):

1. [`WrappedAmount`](src/main/java/io/compprov/examples/nav/wrapped/WrappedAmount.java) /
   [`WrappedRate`](src/main/java/io/compprov/examples/nav/wrapped/WrappedRate.java) /
   [`WrappedOptionPosition`](src/main/java/io/compprov/examples/nav/wrapped/WrappedOptionPosition.java)
   extend `AbstractWrappedVariable<T>` and declare their operations (`add`, `subtract`, `scale`,
   `convert`, variadic `addBulk`, `payout`) as `Descriptor` constants mapped to computation
   lambdas.
2. [`AmountWrapper`](src/main/java/io/compprov/examples/nav/wrapped/AmountWrapper.java) /
   [`RateWrapper`](src/main/java/io/compprov/examples/nav/wrapped/RateWrapper.java) /
   [`OptionPositionWrapper`](src/main/java/io/compprov/examples/nav/wrapped/OptionPositionWrapper.java)
   implement `VariableWrapper<T>`, the one-method factory the framework calls to instantiate
   tracked variables.
3. [`AmountDeserializer`](src/main/java/io/compprov/examples/nav/wrapped/AmountDeserializer.java)
   is a Jackson 3.x (`tools.jackson.*`) deserializer registered alongside `AmountWrapper` so that
   `Amount` values round-trip through a CPG snapshot's JSON. `Rate` and `OptionPosition` are
   records, so Jackson deserializes them without a custom deserializer.

[`NavEnvironmentCustomizer`](src/main/java/io/compprov/plugin/example/nav/NavEnvironmentCustomizer.java)
ties it together — it's the `EnvironmentCustomizer` that registers all three wrappers on the
shared `ComputationEnvironment`, and it's the class named in
[`META-INF/services/io.compprov.core.EnvironmentCustomizer`](src/main/resources/META-INF/services/io.compprov.core.EnvironmentCustomizer).

### Env var configured chat model

[`EnvVarConfiguredChatModel`](src/main/java/io/compprov/plugin/example/nav/EnvVarConfiguredChatModel.java)
is a langchain4j `ChatModel` created using environment variables — `CHATMODEL_URL`,
`CHATMODEL_API_KEY`, `CHATMODEL_NAME` — and it's the class named in
[`META-INF/services/dev.langchain4j.model.chat.ChatModel`](src/main/resources/META-INF/services/dev.langchain4j.model.chat.ChatModel).
It builds one of two underlying clients depending on `CHATMODEL_URL`:

| If `CHATMODEL_URL` contains `"anthropic"` | Otherwise |
|---|---|
| Uses langchain4j's native `AnthropicChatModel` against the real Claude Messages API. | Uses `OpenAiChatModel` against an OpenAI-compatible chat completions endpoint. |
| Real JSON-schema-enforced structured output (`verdict`/`confidence_score`/`markdown_report`), server-side system-message caching (`cacheSystemMessages(true)`), a 5-minute request timeout, a 32k output token budget. | Requests `"json_object"` mode (a generic JSON object, not a schema-enforced one) and the same token/timeout settings. |

For Claude, this means pointing the three env vars at Anthropic's native API — **not** its
OpenAI-compatibility endpoint:

| Env var | Value |
|---|---|
| `CHATMODEL_URL` | `https://api.anthropic.com/v1/` |
| `CHATMODEL_API_KEY` | your Claude API key |
| `CHATMODEL_NAME` | a Claude model ID, e.g. `claude-sonnet-5`, `claude-opus-5`, `claude-haiku-4-5` |

Anthropic's OpenAI-compatibility endpoint (same base URL, but built for the OpenAI SDK) is
deliberately *not* used here: it's meant for evaluating model capabilities rather than
production use, and it silently ignores `response_format` and prompt caching — exactly the two
things this plugin relies on. Going through langchain4j's native `AnthropicChatModel` instead
gets both for real.

## Building

Requires Java 17+ and Maven. `compprov-core` must be available in your local Maven repository
(build it from [compprov-core](https://github.com/compprov/compprov-core) first, or depend on a
published version):

```bash
mvn package
```

This produces `target/compprov-plugin-example-0.1.0.jar` containing this project's own compiled
classes, `META-INF/services` entries, and `langchain4j-anthropic` (needed for the native Claude
integration, and not provided by the host). `compprov-core` and `langchain4j-open-ai` — along
with `langchain4j-anthropic`'s shared transitive dependencies (`langchain4j-core`,
`langchain4j-http-client*`) — are excluded from the shaded jar because the host already provides
them at runtime; see the `maven-shade-plugin` configuration in [`pom.xml`](pom.xml).

## Using the plugin

Pass the built jar to `compprov-analytics` on the command line:

```bash
export CHATMODEL_URL=https://api.anthropic.com/v1/   # required by EnvVarConfiguredChatModel
export CHATMODEL_API_KEY=your-claude-api-key          # required by EnvVarConfiguredChatModel
export CHATMODEL_NAME=claude-sonnet-5                 # required by EnvVarConfiguredChatModel

java -jar compprov-analytics.jar \
  --cpgpath=path/to/snapshot.json \
  --plugin=target/compprov-plugin-example-0.1.0.jar
```

`compprov-analytics` logs each discovered provider (`Applying customizer: ...`,
`Using chat model`) on startup. From then on, any CPG snapshot containing `Amount`, `Rate`, or
`OptionPosition` input values deserializes and replays correctly, and prompt-based analysis uses
the model at `CHATMODEL_URL`/`CHATMODEL_NAME` — Claude, in the example above.

## Test snapshots

`src/test/resources/snapshots/` holds the CPG fixtures used to exercise compprov-analytics'
fraud-pattern prompts, split into `normal/` (should audit clean) and `malicious/` (should trigger
a detection).

Most of them are generated by the `@Test`-annotated classes in `src/test/java/io/compprov/examples/nav/`
— these aren't real assertions-based unit tests, they're snapshot generators: each one builds a
NAV scenario (profit, payout, NAV calculation), prints its provenance graph via
`ComputationEnvironment.toJson(...)`, and the printed JSON is what gets saved as the
corresponding fixture. Each `*Calculator` has a "malicious" sibling (e.g.
`ProfitCalculatorGasOmission` next to `ProfitCalculator`) that's the same scenario with one
deliberate change — a commented-out subtraction, a skipped currency conversion, a truncated
scale — to inject the specific fraud pattern that fixture is meant to test.

The `metrology.json` / `ciddor-substitution*.json` fixtures are the exception: `metrology.json`
comes from a compprov-core test case rather than this project, and the `ciddor-substitution*`
malicious variants were constructed by hand rather than generated from a calculator class. There
is currently no generator for these in this repo.

## Sample analysis run

`samples/` holds one full `compprov-analytics --executePrompts=true` run (Claude Sonnet 5) over
all twelve fixtures above — raw output in `samples/outputs/llm-api/sonnet-5/<timestamp>/out/`,
one subfolder per input file, plus a top-level `summary.md`. Each fixture is run through five
LLM prompts/alarms — Calculation omission, Lineage disconnection, Precision tampering, Semantic
violation, Double counting — each returning `CLEAN` or a named detection verdict with a
confidence score.

All eight `malicious/` fixtures triggered a detection as expected. Of the four `normal/`
fixtures, `payout.json` came back `CLEAN` on all five alarms; the other three each tripped one
alarm — not because the underlying calculation is wrong, but because the model surfaced a real,
immaterial quirk in this example codebase:

- **`metrology.json`** — Precision tampering: `SUSPICIOUS LOGIC (72.0)`. One operation
  (`Exp_double`, computing saturation vapor pressure) carries no `MathContext` argument and is
  evaluated at IEEE-754 `double` precision, unlike every neighboring operation in the same
  34-digit `BigDecimal` chain. A genuine, isolated precision gap — but per the report's own
  materiality check, the resulting error is many orders of magnitude below what the final result
  needs. This fixture predates this repo (see [Test snapshots](#test-snapshots)), so there's no
  local source to fix.
- **`net_asset_value.json`** — Precision tampering: `SUSPICIOUS LOGIC (74.0)`. Every
  `WrappedAmount.convert` call truncates instead of rounding to the nearest cent — exactly
  [`Amount`](src/main/java/io/compprov/examples/nav/model/Amount.java)'s documented
  round-down-always policy, not a bug. But that policy lives inside `Amount`/`Rate` rather than
  being declared as an explicit `MathContext` argument on the CPG operation, so the model has no
  in-graph evidence the truncation is intentional and flags it for review. Same root cause as
  `profit.json`'s precision finding below.
- **`profit.json`** — Precision tampering: `SUSPICIOUS LOGIC (58.0)`, the same truncate-vs-round
  gap as `net_asset_value.json` (two 1-unit roundings that happen to cancel out in the final
  total). Semantic violation: `CONTEXT MISMATCH (63.0)` —
  [`ProfitCalculator`](src/test/java/io/compprov/examples/nav/ProfitCalculator.java) labels one
  yield `"wBTC yield"` but stores it as a plain `BTC`-currency `Amount`, since
  [`Currency`](src/main/java/io/compprov/examples/nav/model/Currency.java) has no separate `WBTC`
  entry — this example treats wrapped BTC as pegged 1:1 to native BTC. The model correctly
  noticed that the graph has no explicit node recording that peg assumption.

In each case the model itself down-graded the verdict from a confirmed-fraud category (`ANOMALY
DETECTED` / `SEMANTIC CAST DETECTED`) to a lower-confidence advisory one (`SUSPICIOUS LOGIC` /
`CONTEXT MISMATCH`) after checking materiality and scalability — the intended behavior for
findings a human reviewer should be able to dismiss quickly, rather than a silent false negative.

## Project layout

```
src/main/java/io/compprov/examples/nav/
├── model/              Plain domain types: Amount, Currency, Rate, OptionPosition, OptionType
└── wrapped/             compprov-core integration: WrappedAmount, WrappedRate, WrappedOptionPosition,
                          AmountWrapper, RateWrapper, OptionPositionWrapper, AmountDeserializer

src/main/java/io/compprov/plugin/example/nav/
├── NavEnvironmentCustomizer.java    EnvironmentCustomizer SPI provider
└── EnvVarConfiguredChatModel.java   ChatModel SPI provider

src/main/resources/META-INF/services/
├── io.compprov.core.EnvironmentCustomizer
└── dev.langchain4j.model.chat.ChatModel
```

## License

Apache License 2.0 — see [LICENSE](LICENSE).
