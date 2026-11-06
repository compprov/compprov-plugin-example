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
- [Sample analysis runs](#sample-analysis-runs)
- [Project layout](#project-layout)
- [License](#license)

---

## How plugins work

`compprov-analytics` accepts one or more `--plugin=<path-to-jar>` command-line arguments. For
each plugin jar, it opens a `URLClassLoader` over the jar and asks `ServiceLoader` for three kinds
of providers declared under `META-INF/services`:

| Service interface | Purpose |
|---|---|
| `io.compprov.core.EnvironmentCustomizer` | Given the shared `ComputationEnvironment`, register additional type wrappers, serializers, etc. |
| `dev.langchain4j.model.chat.ChatModel` | Supplies the LLM used for prompt-based analysis of a CPG in `PROCESS_SYNC` mode. |
| `dev.langchain4j.model.chat.BatchChatModel` | Supplies the LLM used in the batch modes (`EXECUTE_ASYNC`, `PROCESS_ASYNC`, `CONTINUE_ASYNC`). |

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

### Env var configured chat models

[`EnvVarConfiguredChatModel`](src/main/java/io/compprov/plugin/example/nav/EnvVarConfiguredChatModel.java)
(a langchain4j `ChatModel`) and
[`EnvVarConfiguredBatchedChatModel`](src/main/java/io/compprov/plugin/example/nav/EnvVarConfiguredBatchedChatModel.java)
(a `BatchChatModel`) are configured from environment variables and named in
[`META-INF/services`](src/main/resources/META-INF/services). Each builds one of two clients
depending on `CHATMODEL_URL`:

| | `CHATMODEL_URL` contains `"anthropic"` | Otherwise (OpenAI-compatible endpoint) |
|---|---|---|
| Sync (`PROCESS_SYNC`) | langchain4j's native `AnthropicChatModel` | `OpenAiChatModel` |
| Batch | `AnthropicBatchChatModel` (Message Batches API) | [`OpenAiBatchChatModel`](src/main/java/io/compprov/plugin/example/nav/OpenAiBatchChatModel.java) (OpenAI Files + Batches API; langchain4j-open-ai has no batch model) |
| Caching | System-message caching on | Automatic prompt caching |

Both request JSON-schema structured output (`risk_score` / `markdown_report`, the format
compprov-analytics expects) and use a 5-minute request timeout.

| Env var | Required | Description |
|---|---|---|
| `CHATMODEL_URL` | To call a model | Base URL, e.g. `https://api.anthropic.com/v1/` or `https://api.openai.com/v1/` |
| `CHATMODEL_API_KEY` | To call a model | API key |
| `CHATMODEL_NAME` | To call a model | Model ID, e.g. `claude-sonnet-5`, `claude-haiku-5-5`, `gpt-6-luna` |
| `CHATMODEL_MAXTOKENS` | No, default `100000` | Output token limit per request |
| `CHATMODEL_EFFORT` | No | OpenAI `reasoning_effort`, e.g. `medium`; ignored for Claude |
| `CHATMODEL_CACHE_TTL` | No, default `5m` | Claude batch only: system-message cache TTL, `5m` or `1h`. Batch requests run in no particular order, so `1h` can raise cache hits on long batches |

The clients are built on first use, not when the plugin loads. In `GENERATE_MARKDOWN` mode no
model is called, so none of these variables is needed; in the other modes the first call fails
with a clear message if `CHATMODEL_URL` is missing.

For Claude, point `CHATMODEL_URL` at Anthropic's native API, **not** its OpenAI-compatibility
endpoint: that endpoint is meant for evaluating capabilities rather than production use, and it
silently ignores `response_format` and prompt caching — exactly the two things this plugin
relies on.

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

Pass the built jar to `compprov-analytics` on the command line.

Deterministic checks and markdown prompts only (no model, no env vars needed):

```bash
java -jar compprov-analytics.jar \
  --cpgfolder=samples/snapshots \
  --plugin=target/compprov-plugin-example-0.1.0.jar
```

Sending the prompts to Claude (the configuration recommended by the
[compprov-analytics evaluation](https://github.com/compprov/compprov-analytics#evaluation)):

```bash
export CHATMODEL_URL=https://api.anthropic.com/v1/
export CHATMODEL_API_KEY=your-claude-api-key
export CHATMODEL_NAME=claude-sonnet-5

java -jar compprov-analytics.jar \
  --cpgfolder=samples/snapshots \
  --plugin=target/compprov-plugin-example-0.1.0.jar \
  --promptProcessingMode=PROCESS_SYNC \
  --llmTemplates=single_prompt \
  --threads=4
```

Use `--promptProcessingMode=PROCESS_ASYNC` instead to send everything as one batch at the
providers' batch discount, or `EXECUTE_ASYNC` and later `CONTINUE_ASYNC --workDir=<run>/out` to
submit now and collect the results in another session.

`compprov-analytics` logs each discovered provider (`Applying customizer: ...`,
`Using chat model`, `Using batch chat model`) on startup. From then on, any CPG snapshot
containing `Amount`, `Rate`, or `OptionPosition` input values deserializes and replays
correctly.

## Test snapshots

`samples/snapshots/` holds the 116 CPG fixtures used to evaluate compprov-analytics:
`normal/` (33, should audit clean), `malicious/` (39, should trigger a detection), `gptscan/` (5)
and `numscout/` (5) with patterns from published smart-contract auditing tools, and `defects4j/`
(17 real bugs in `buggy/`, their 17 fixes in `fixed/`).

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
anything here.

### `gptscan/` and `numscout/`

Two more fixture sets sit alongside `normal/`/`malicious/` rather than under them, each
reimplementing attack patterns from a published smart-contract auditing tool as a CPG:

- [`gptscan/`](samples/snapshots/gptscan) — five DeFi lending/vault scenarios (collateral
  valuation, governance vote weight, interest accrual distribution, transfer checkpoint, vault
  first deposit) generated by `*SemanticTypeAttack`-suffixed classes under
  `src/test/java/io/compprov/examples/gptscan/`. Named after
  [GPTScan](https://arxiv.org/abs/2308.03314), a logic-bug-detection tool for smart contracts;
  each fixture reproduces one of its bug patterns, e.g. `CollateralValuationCalculatorSemanticTypeAttack`
  pricing collateral off raw, single-block AMM pool reserves instead of a verified oracle. The
  same folder also has prototype generators for three more patterns (`ApprovalNotCleared*`,
  `FrontRunning*`, `Slippage*`); they don't model those mechanics faithfully enough, so their
  output is not part of the fixture set.
- [`numscout/`](samples/snapshots/numscout) — five AMM/staking/game scenarios, each a
  `*PrecisionTampering` class under `src/test/java/io/compprov/examples/numscout/` (AMM taker
  fee, game winner payout, liquidity pool join, staking reward fee, token sale threshold). The
  same folder holds a correct `GameWinnerPayoutCalculator` baseline, which is not part of the
  fixture set. Named after the [NumScout](https://arxiv.org/abs/2503.10041) paper's
  precision-loss taxonomy — each fixture injects one specific rounding-direction defect (e.g.
  floor division where the protocol requires ceiling division) at `BigInteger` wei-scale rather
  than the `BigDecimal` scale the other fixtures use.

These use `DefaultComputationContext`/`BigInteger` wrapping directly rather than this plugin's NAV
domain types, so they don't exercise the plugin's `EnvironmentCustomizer` — they're here as
additional fraud-pattern coverage for `compprov-analytics` itself.

### `defects4j/`

Naturally occurring defects: real numerical bugs from the [Defects4J](https://github.com/rjust/defects4j)
benchmark (Apache Commons Math and JFreeChart), each rebuilt as a CPG pair — `fixed/` (the patched
computation) and `buggy/` (the original defect), same filename in both. Generated by classes under
`src/test/java/io/compprov/examples/defects4j/`, named `<Project><id>*Calculator` / `*CalculatorBuggy`
and fed the bug's own Defects4J test data, so each pair reproduces the test's expected and buggy
values. Where a defect sits inside a larger algorithm (an optimizer, solver or integrator), the CPG
covers the step the fix changed; where the test's exact input doesn't trigger the defect under
DECIMAL64, the data provider's Javadoc says what was used instead.

| File | Defects4J / issue | Defect |
|---|---|---|
| `math-11.json` | Math-11 / [MATH-929](https://issues.apache.org/jira/browse/MATH-929) | Multivariate normal density's (2π)^(−k/2) exponent uses integer division, truncating −1/2 to 0 for k = 1 (0.12952 → 0.32465) |
| `math-18.json` | Math-18 / [MATH-867](https://issues.apache.org/jira/browse/MATH-867) | CMA-ES bound encoding shifts by the lower bound, (x − lo)/(hi − lo); with lo = −5e16 the shift swallows the candidate's digits (11.1 → 10) |
| `math-41.json` | Math-41 / [MATH-704](https://issues.apache.org/jira/browse/MATH-704) | Weighted variance sums Σw over the whole weights array instead of the evaluated segment (1.66445 → 0.31909) |
| `math-55.json` | Math-55 / [MATH-554](https://issues.apache.org/jira/browse/MATH-554) | Cross product of nearly parallel vectors computed without preconditioning; the z component cancels (1 → 0) |
| `math-57.json` | Math-57 / [MATH-546](https://issues.apache.org/jira/browse/MATH-546) | k-means++ seeding keeps ΣD(x)² in an `int`, so small distances truncate to 0 and the first point is always chosen (point 8 → point 0) |
| `math-65.json` | Math-65 / [MATH-377](https://issues.apache.org/jira/browse/MATH-377) | Weighted χ² divides r² by the weight instead of multiplying, so the parameter-error scale is off by a factor of w (1.44378 → 0.72189) |
| `math-69.json` | Math-69 / [MATH-371](https://issues.apache.org/jira/browse/MATH-371) | Pearson p-value 2·(1 − CDF(t)) cancels to 0 once CDF(t) rounds to 1; fix uses 2·CDF(−t) (4.8958E-18 → 0) |
| `math-72.json` | Math-72 / [MATH-344](https://issues.apache.org/jira/browse/MATH-344) | Brent solver's endpoint early exit returns f(min) instead of min (π → 1.2246E-16) |
| `math-74.json` | Math-74 / [MATH-338](https://issues.apache.org/jira/browse/MATH-338) | Runge-Kutta error scale drops the relTol·\|y\| term, halving the initial step size (0.04218 → 0.02021) |
| `math-75.json` | Math-75 / [MATH-329](https://issues.apache.org/jira/browse/MATH-329) | `Frequency.getPct(v)` returns the cumulative percentage of values ≤ v instead of the share equal to v (0.5 → 1) |
| `math-77.json` | Math-77 / [MATH-326](https://issues.apache.org/jira/browse/MATH-326) | L∞ norm accumulates the running maximum with `+=` instead of replacing it (6 → 128) |
| `math-91.json` | Math-91 / [MATH-252](https://issues.apache.org/jira/browse/MATH-252) | `Fraction.compareTo` compares rounded double values instead of exact cross products, so two different π approximations compare equal (−1 → 0) |
| `math-92.json` | Math-92 / [MATH-241](https://issues.apache.org/jira/browse/MATH-241) | Binomial coefficient via floor(exp(Σ ln) + 0.5) rounds to the wrong integer (C(48, 23): 30957699535776 → 30957699535775) |
| `math-93.json` | Math-93 / [MATH-240](https://issues.apache.org/jira/browse/MATH-240) | Factorial via floor(exp(Σ ln i) + 0.5) is off in the last digits (17!: 355687428096000 → 355687428096003) |
| `math-102.json` | Math-102 / [MATH-175](https://issues.apache.org/jira/browse/MATH-175) | χ² statistic doesn't rescale expected counts when their total differs from the observed total (9.02331 → 16.41311) |
| `math-105.json` | Math-105 / [MATH-85](https://issues.apache.org/jira/browse/MATH-85) | Regression SSE = Syy − Sxy²/Sxx isn't clamped at 0, so cancellation on collinear data goes negative (0 → −3E-14) |
| `chart-24.json` | Chart-24 (JFreeChart) | `GrayPaintScale` clamps the lookup value but computes the gray level from the raw one (g = 0 → −127, so `java.awt.Color` throws) |

## Sample analysis runs

The fixture set was run through compprov-analytics with 9 models and several prompt
configurations: 40 runs, 7,191 model calls. The results, what the tool finds well and which
models work best are summarized in the
[compprov-analytics evaluation](https://github.com/compprov/compprov-analytics#evaluation).

Runs are stored as `samples/outputs/llm-api/<model>_<effort>/<configuration>/<timestamp>/out/`,
where the configuration is `single_prompt`, `three_prompts`, `single_prompt_no_ids` or
`single_prompt_minified`. Each `out/` has a top-level `summary.md` with the verdicts and one
subfolder per fixture holding the check outputs, the prompts sent, the raw and rendered model
response, and `*_call_details.json` with token usage and timing.

This repository keeps:

- one complete run, `claude-sonnet-5/single_prompt/2026-11-03_20-22-55.586` (single prompt, the
  best-scoring run: 64 of 66 positives flagged, 4 of 50 negatives);
- the top-level `summary.md` of every other run.

The complete outputs of all runs are archived on Zenodo: *TODO: DOI*. The prompts and check
outputs left out here are regenerated byte for byte by running the same compprov-analytics
version with the same flags on `samples/snapshots`.

`samples/override/` holds the prompt overrides used for the `*_no_ids` runs, each folder with an
`args.txt` listing the flags.

## Project layout

```
src/main/java/io/compprov/examples/nav/
├── model/              Plain domain types: Amount, Currency, Rate, OptionPosition, OptionType
└── wrapped/             compprov-core integration: WrappedAmount, WrappedRate, WrappedOptionPosition,
                          AmountWrapper, RateWrapper, OptionPositionWrapper, AmountDeserializer

src/main/java/io/compprov/plugin/example/nav/
├── NavEnvironmentCustomizer.java           EnvironmentCustomizer SPI provider
├── EnvVarConfiguredChatModel.java          ChatModel SPI provider
├── EnvVarConfiguredBatchedChatModel.java   BatchChatModel SPI provider
├── OpenAiBatchChatModel.java               OpenAI Batch API client used by the batch provider
└── ChatModelSettings.java                  CHATMODEL_* environment variables

src/main/resources/META-INF/services/
├── io.compprov.core.EnvironmentCustomizer
├── dev.langchain4j.model.chat.ChatModel
└── dev.langchain4j.model.chat.BatchChatModel

src/test/java/io/compprov/examples/       Fixture generators, see Test snapshots

samples/
├── snapshots/                            The 116 CPG fixtures
├── outputs/                              Analysis runs, see Sample analysis runs
└── override/                             Prompt overrides used by some runs
```

## License

Apache License 2.0 — see [LICENSE](LICENSE).
