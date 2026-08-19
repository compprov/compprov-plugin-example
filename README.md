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
- [`Currency`](src/main/java/io/compprov/examples/nav/model/Currency.java) — `BTC`, `WBTC`, `ETH`,
  `USDC`, `USDT`, `USD`, `EUR`, `WSTETH`, each with its own decimal precision. `WBTC` is tracked
  separately from `BTC` and only ever converted between the two via an explicit peg `Rate`, never
  treated as interchangeable.
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
| Real JSON-schema-enforced structured output (`verdict`/`confidence_score`/`markdown_report`), server-side system-message caching (`cacheSystemMessages(true)`), a 5-minute request timeout, a 100k output token budget. | Requests `"json_object"` mode (a generic JSON object, not a schema-enforced one) and the same token/timeout settings. |

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

You don't have to build this yourself — every [GitHub release](../../releases) has the built jar
(`compprov-plugin-example-<version>.jar`) attached, produced by the
[`Release` workflow](.github/workflows/release.yml). Download it and skip straight to
[Using the plugin](#using-the-plugin).

To build from source instead, you'll need Java 17+ and Maven. `compprov-core` must be available
in your local Maven repository (build it from
[compprov-core](https://github.com/compprov/compprov-core) first, or depend on a published
version):

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

`samples/snapshots/` holds the CPG fixtures used to exercise compprov-analytics' fraud-pattern
prompts, split into `normal/` (should audit clean) and `malicious/` (should trigger a detection).

Every fixture is generated by an `@Test`-annotated class under `src/test/java/io/compprov/examples/`
— these aren't real assertions-based unit tests, they're snapshot generators: each one builds a
calculation (one per domain — NAV, payroll, insurance claims, loan amortization, e-commerce
orders, plus a batch of other business and physics/chemistry/engineering domains), prints its
provenance graph via `ComputationEnvironment.toJson(...)`, and the printed JSON is what gets saved
as the corresponding fixture. Run one directly with, e.g.:

```bash
mvn test -Dtest=io.compprov.examples.payroll.PayrollCalculator
```

### `normal/`

One file per domain, 33 in total (`payroll.json`, `insurance.json`, `idealgas.json`, ...), named
after the domain's test package — business domains (payroll, insurance, loans, e-commerce,
construction, subscriptions, ...) alongside physics/chemistry/engineering ones (ideal gas law,
free fall, orbital period, pendulum period, heat conduction, solution dilution, ...). Every
calculation here is deliberately correct, with no injected defects, to serve as true-negative
fixtures. The three NAV scenarios keep their original names (`profit.json`, `payout.json`,
`net_asset_value.json`). `metrology.json` is the one exception: it comes from a `compprov-core`
test case rather than this project, so there's no local generator for it.

### `malicious/`

Organized into one subfolder per fraud-detection prompt compprov-analytics runs, each holding one
malicious sibling per domain that supports it (same domain-based filename as `normal/`):

| Subfolder | Attack | Generator suffix |
|---|---|---|
| `calculation_omission/` | A mandatory debit/credit entry is computed correctly, then dropped from the final aggregation | `*Omission` |
| `lineage_disconnection/` | A correctly-derived value is swapped for a disconnected literal with no edge back to its root inputs | `*LineageDisconnection` |
| `precision_tampering/` | One operation in a chain silently uses a different `MathContext`/rounding than its neighbors | `*PrecisionTampering` (NAV: `PayoutCalculatorSlicing`) |
| `semantic_tamper/` | A value's descriptor label contradicts how it's actually used/classified in the graph | `*SemanticTamper` |
| `double_counting/` | One value is fed into an aggregation through two parallel paths | `*DoubleCounting` |

Each generator is the same scenario as its `normal/` counterpart with one deliberate change — a
commented-out subtraction, a disconnected literal, a mismatched `MathContext`, a mislabeled value,
a duplicated aggregation path — to inject exactly the fraud pattern that subfolder is meant to
test. Not every `normal/` domain has a malicious sibling in every subfolder — only the ones whose
calculation shape actually supports that attack (e.g. `double_counting/` needs an aggregation with
a value reachable through two paths, which not every domain has).

`malicious/legacy/` holds two fixtures that predate the per-domain generators and have no
corresponding `@Test` class in this repo — `broken-chronology.json` and
`nav_invalid_calculation.json` (an early NAV fixture with a deliberately corrupted aggregate that
doesn't replay from its own recorded operations) — kept for reference but not regenerated by
anything here. The Ciddor-equation substitution fixtures that used to live here
(`ciddor-substitution.json`, `ciddor-substitution-1-1.json`) have since moved into
`lineage_disconnection/`, since that's the attack they actually demonstrate.

### `gptscan/` and `numscout/`

Two more fixture sets sit alongside `normal/`/`malicious/` rather than under them, each
reimplementing attack patterns from a published smart-contract auditing tool as a CPG:

- [`gptscan/`](samples/snapshots/gptscan) — five DeFi lending/vault scenarios (collateral
  valuation, governance vote weight, interest accrual distribution, transfer checkpoint, vault
  first deposit) generated by `*SemanticTypeAttack`-suffixed classes under
  `src/test/java/io/compprov/examples/gptscan/`. Named after
  [GPTScan](https://arxiv.org/abs/2308.03314), a logic-bug-detection tool for smart contracts;
  each fixture reproduces one of its bug patterns, e.g. `CollateralValuationCalculatorSemanticTypeAttack`
  pricing collateral off raw, single-block AMM pool reserves instead of a verified oracle.
- [`numscout/`](samples/snapshots/numscout) — six AMM/staking scenarios generated by classes
  under `src/test/java/io/compprov/examples/numscout/`: one correct baseline
  (`GameWinnerPayoutCalculator`) plus five `*PrecisionTampering` variants (AMM taker fee,
  liquidity pool join, staking reward fee, token sale threshold, and a tampered variant of the
  baseline itself). Named after the [NumScout](https://arxiv.org/abs/2404.11831) paper's
  precision-loss taxonomy — each fixture injects one specific rounding-direction defect (e.g.
  floor division where the protocol requires ceiling division) at `BigInteger` wei-scale rather
  than the `BigDecimal` scale the other fixtures use.

These use `DefaultComputationContext`/`BigInteger` wrapping directly rather than this plugin's NAV
domain types, so they don't exercise the plugin's `EnvironmentCustomizer` — they're here as
additional fraud-pattern coverage for `compprov-analytics` itself.

## Sample analysis run

`samples/outputs/llm-api/sonnet-5/` holds four full `compprov-analytics --executePrompts=true`
runs (Claude Sonnet 5), one per fixture category — `normal/`, `malicious/`, `gptscan/`,
`numscout/` — each under its own `<timestamp>/out/`, with one subfolder per input file plus a
top-level `summary.md`. Every fixture is run through five LLM prompts/alarms — Calculation
omission, Lineage disconnection, Precision tampering, Semantic violation, Double counting — each
returning `CLEAN` or a named detection verdict with a confidence score.
`samples/outputs/llm-manual/` holds one additional run over the full `malicious/` set in
prompt-dump mode: instead of calling the configured chat model, `compprov-analytics` writes each
prompt to a `*_prompt.md` file for pasting into a chat UI by hand, so its `summary.md` has no
verdicts (`—` in every alarm column) — a way to exercise this plugin without needing
`CHATMODEL_API_KEY` set.

As of the runs captured here: every one of the 39 `malicious/` fixtures and all 5 `gptscan/`
fixtures trip at least one detection (not always the alarm matching their own category — e.g. a
`lineage_disconnection/` fixture may also flag Calculation omission). 32 of the 33 `normal/`
fixtures, and 4 of the 5 `numscout/` fixtures analyzed, come back fully `CLEAN` across all five
alarms. The two exceptions are real, worth knowing about before extending this project further:

- **`normal/metrology.json`** — flags Calculation omission and Precision tampering
  (`SUSPICIOUS LOGIC`). One operation (`Exp_double`, computing saturation vapor pressure) carries
  no `MathContext` argument and is evaluated at IEEE-754 `double` precision, unlike every
  neighboring operation in the same 34-digit `BigDecimal` chain. A genuine, isolated precision
  gap — but per the report's own materiality check, the resulting error is many orders of
  magnitude below what the final result needs. This fixture comes from `compprov-core`, not this
  project, so there's no local generator to fix it from.
- **`numscout/amm_taker_fee_precision_tampering.json`** — comes back fully `CLEAN` despite
  [`AmmTakerFeeCalculatorPrecisionTampering`](src/test/java/io/compprov/examples/numscout/AmmTakerFeeCalculatorPrecisionTampering.java)
  deliberately using floor division where the pool requires ceiling division. At the fixture's
  `BigInteger` wei-scale inputs the resulting one-unit understatement is too small for the model
  to flag as material — a known false negative worth keeping in mind when judging detection
  results on very small-magnitude fixtures.

Two `net_asset_value.json`/`profit.json`-shaped issues that used to show up here have since been
fixed in the source, and both now come back fully `CLEAN`:

- Every [`Amount`](src/main/java/io/compprov/examples/nav/model/Amount.java) operation truncates
  instead of rounding to the nearest cent — a deliberate, documented balance-safety policy (see
  `Amount`'s class Javadoc), not a bug. But that policy used to live only inside `Amount`,
  invisible to the CPG, so the model had no in-graph evidence the truncation was intentional and
  flagged it as suspicious precision tampering. **Fixed**: `TestComputationContext` (the shared
  test harness under `src/test/java/io/compprov/examples/`) now records the truncate-always policy
  and every `Currency`'s decimal precision as metadata on the root descriptor of every generated
  graph, once per graph rather than once per operation — giving the model the declared
  justification it was missing.
- [`ProfitCalculator`](src/test/java/io/compprov/examples/profit/ProfitCalculator.java) used to
  label one yield `"wBTC yield"` but store it as a plain `BTC`-currency `Amount`, since
  [`Currency`](src/main/java/io/compprov/examples/nav/model/Currency.java) had no separate `WBTC`
  entry — silently treating wrapped BTC as pegged 1:1 to native BTC with no node recording that
  assumption, which the model flagged as a semantic-type violation. **Fixed**: `Currency` now has
  a distinct `WBTC` entry, and `ProfitCalculator` converts the AAVE `WBTC` yield through an
  explicit `WBTC/BTC` peg `Rate` before the `BTC/USDC` conversion — the same pattern already used
  for `WSTETH`/`ETH` elsewhere in this example.

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
