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

Any provider found is applied automatically — no other wiring is required. A plugin jar only
needs to contain its own compiled classes and a `META-INF/services` entry per interface it
implements; `compprov-core` and `langchain4j-open-ai` are declared with `provided` scope in
[`pom.xml`](pom.xml) because they're already on the classpath `compprov-analytics` loads
plugins with.

## What this plugin provides

### NAV (Net Asset Value) domain type wrappers

`io.compprov.examples.nav` is a small, framework-agnostic domain model for a multi-currency
portfolio:

- [`Amount`](src/main/java/io/compprov/examples/nav/model/Amount.java) — a `Currency` plus a
  `BigDecimal`, scaled to that currency's decimal precision.
- [`Rate`](src/main/java/io/compprov/examples/nav/model/Rate.java) — an exchange rate between
  two currencies, used by `Amount.convert(Rate)`.
- [`Currency`](src/main/java/io/compprov/examples/nav/model/Currency.java) — `BTC`, `ETH`,
  `USDC`, `USD`, `WSTETH`, each with its own decimal precision.

`io.compprov.examples.nav.wrapped` integrates these types with compprov-core **without changing
them**, following the three-step "custom type wrapper" pattern (see the "Extending with custom
type wrappers" section of the compprov-core README for the full walkthrough):

1. [`WrappedAmount`](src/main/java/io/compprov/examples/nav/wrapped/WrappedAmount.java) /
   [`WrappedRate`](src/main/java/io/compprov/examples/nav/wrapped/WrappedRate.java) extend
   `AbstractWrappedVariable<T>` and declare their operations (`add`, `convert`, variadic
   `addBulk`) as `Descriptor` constants mapped to computation lambdas.
2. [`AmountWrapper`](src/main/java/io/compprov/examples/nav/wrapped/AmountWrapper.java) /
   [`RateWrapper`](src/main/java/io/compprov/examples/nav/wrapped/RateWrapper.java) implement
   `VariableWrapper<T>`, the one-method factory the framework calls to instantiate tracked
   variables.
3. [`AmountDeserializer`](src/main/java/io/compprov/examples/nav/wrapped/AmountDeserializer.java)
   is a Jackson 3.x (`tools.jackson.*`) deserializer registered alongside `AmountWrapper` so that
   `Amount` values round-trip through a CPG snapshot's JSON.

[`NavEnvironmentCustomizer`](src/main/java/io/compprov/plugin/example/nav/NavEnvironmentCustomizer.java)
ties it together — it's the `EnvironmentCustomizer` that registers both wrappers on the shared
`ComputationEnvironment`, and it's the class named in
[`META-INF/services/io.compprov.core.EnvironmentCustomizer`](src/main/resources/META-INF/services/io.compprov.core.EnvironmentCustomizer).

### Env var configured chat model

[`EnvVarConfiguredChatModel`](src/main/java/io/compprov/plugin/example/nav/EnvVarConfiguredChatModel.java)
is a langchain4j `ChatModel` created using environment variables. It reads its API key from the
`CHATMODEL_API_KEY` environment variable, name from `CHATMODEL_NAME` and URL from `CHATMODEL_URL`,
and passes them to langchain4j's `OpenAiChatModel`, so `CHATMODEL_URL` must point at an
OpenAI-compatible chat completions endpoint. It is the class named in
[`META-INF/services/dev.langchain4j.model.chat.ChatModel`](src/main/resources/META-INF/services/dev.langchain4j.model.chat.ChatModel).

Because the [Claude API exposes an OpenAI-compatible endpoint](https://platform.claude.com/docs/en/api/openai-sdk),
these same three env vars can be pointed at Claude instead of OpenAI:

| Env var | Value |
|---|---|
| `CHATMODEL_URL` | `https://api.anthropic.com/v1/` |
| `CHATMODEL_API_KEY` | your Claude API key |
| `CHATMODEL_NAME` | a Claude model ID, e.g. `claude-opus-4-8` |

Note that this compatibility layer is meant for evaluating model capabilities, not as a
production integration path — some OpenAI-only request fields (`strict`, prompt caching, etc.)
are silently ignored, and extended thinking output isn't returned. See the linked docs for the
full list of supported/ignored fields.

## Building

Requires Java 17+ and Maven. `compprov-core` must be available in your local Maven repository
(build it from [compprov-core](https://github.com/compprov/compprov-core) first, or depend on a
published version):

```bash
mvn package
```

This produces `target/compprov-plugin-example-0.1.0.jar` — a plain jar containing only this
project's own compiled classes and `META-INF/services` entries, since its dependencies are all
`provided` at runtime by the host application.

## Using the plugin

Pass the built jar to `compprov-analytics` on the command line:

```bash
export CHATMODEL_URL=https://api.anthropic.com/v1/   # required by EnvVarConfiguredChatModel
export CHATMODEL_API_KEY=your-claude-api-key          # required by EnvVarConfiguredChatModel
export CHATMODEL_NAME=claude-opus-4-8                 # required by EnvVarConfiguredChatModel

java -jar compprov-analytics.jar \
  --cpgpath=path/to/snapshot.json \
  --plugin=target/compprov-plugin-example-0.1.0.jar
```

`compprov-analytics` logs each discovered provider (`Applying customizer: ...`,
`Using chat model`) on startup. From then on, any CPG snapshot containing `Amount` or `Rate`
input values deserializes and replays correctly, and prompt-based analysis uses the model at
`CHATMODEL_URL`/`CHATMODEL_NAME` — Claude, in the example above.

## Project layout

```
src/main/java/io/compprov/examples/nav/
├── model/              Plain domain types: Amount, Currency, Rate
└── wrapped/             compprov-core integration: WrappedAmount, WrappedRate,
                          AmountWrapper, RateWrapper, AmountDeserializer

src/main/java/io/compprov/plugin/example/nav/
├── NavEnvironmentCustomizer.java    EnvironmentCustomizer SPI provider
└── EnvVarConfiguredChatModel.java   ChatModel SPI provider

src/main/resources/META-INF/services/
├── io.compprov.core.EnvironmentCustomizer
└── dev.langchain4j.model.chat.ChatModel
```

## License

Apache License 2.0 — see [LICENSE](LICENSE).
