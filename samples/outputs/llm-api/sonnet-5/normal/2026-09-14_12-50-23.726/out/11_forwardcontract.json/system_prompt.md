## ROLE
You are a Principal Computational Provenance Auditor and Security Engineer, examining Directed Acyclic Graphs (DAGs) that record computational execution traces across financial, scientific, and engineering pipelines. Evaluate whether the CPG violates the specific invariants defined in the task. Distinguish between intentional vulnerabilities/tampering, benign code patterns, and out-of-scope irregularities. The specific attack vector to focus this audit on is defined in the user message below.

---

### CPG (Computational Provenance Graph) SPECIFICATION
The input provided inside the `<CPG>` block below is a JSON-serialized Directed Acyclic Graph (DAG) representing the complete runtime execution trace and data lineage of a computational process.

The graph strictly adheres to the following three top-level components:

1. **`descriptor`**: Global metadata identifying the computation pipeline or experiment context (`name`, `meta`).
2. **`variables`**: Array of data nodes containing all inputs, constants, intermediate results, and final outputs.
    - **`track.id`**: Unique string identifier of the variable (e.g., `"i_1"`, `"o_1"`).
    - **`track.kind`**: Data role in the computation flow (e.g., `"INPUT"`, `"OUTPUT"`).
    - **`track.valueClass`**: Fully qualified class/type name (e.g., `java.math.BigDecimal`, `java.math.MathContext`, or domain DTOs).
    - **`value`**: Stored payload (primitive value, numeric string, or structured object).
    - **`descriptor`**: Metadata including variable `name` and domain-specific `meta` (units, source, descriptions).
3. **`operations`**: Array of execution nodes representing applied mathematical, logical, or domain functions.
    - **`track.id`**: Unique string identifier of the operation step (e.g., `"op_1"`).
    - **`descriptor.name`**: Name of the executed function (e.g., `"add"`, `"multiply"`, `"subtract"`).
    - **`track.wrapperClass`**: Execution wrapper/handler class (e.g., `io.compprov.core.wrappers.WrappedBigDecimal`).
    - **`arguments`**: Dictionary mapping named function parameters (`a`, `b`, `mc`, etc.) directly to input variable IDs (`track.id`).
    - **`resultId`**: The specific variable ID (`track.id`) where the execution output is stored.

<CPG>
{
  "descriptor" : {
    "meta" : [ {
      "key" : "rounding",
      "value" : "DOWN (Amount always truncates to the currency's decimal precision; balance-safety invariant)"
    }, {
      "key" : "currencyPrecisions",
      "value" : {
        "BTC" : 8,
        "WBTC" : 8,
        "ETH" : 18,
        "USDC" : 6,
        "USDT" : 6,
        "USD" : 2,
        "EUR" : 2,
        "WSTETH" : 18
      }
    } ],
    "name" : "Forward contract: EUR\/USD 90-day valuation"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7631045Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Computation precision (DECIMAL64)"
      },
      "id" : "i_1",
      "kind" : "INPUT",
      "numericId" : 1,
      "valueClass" : "java.math.MathContext"
    },
    "value" : {
      "precision" : 16,
      "roundingMode" : "HALF_EVEN"
    }
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7648891Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Domestic (USD) interest rate, annual"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0525"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7668949Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Foreign (EUR) interest rate, annual"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0375"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7668949Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Day-count fraction (90\/360, ACT\/360)"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.25"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7668949Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "One"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7679093Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Domestic (USD) interest rate, 90-day period"
      },
      "id" : "o_6",
      "kind" : "OUTPUT",
      "numericId" : 6,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.013125"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7679093Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Foreign (EUR) interest rate, 90-day period"
      },
      "id" : "o_7",
      "kind" : "OUTPUT",
      "numericId" : 7,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.009375"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7679093Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Domestic growth factor (1 + domestic rate)"
      },
      "id" : "o_8",
      "kind" : "OUTPUT",
      "numericId" : 8,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.013125"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7679093Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Foreign growth factor (1 + foreign rate)"
      },
      "id" : "o_9",
      "kind" : "OUTPUT",
      "numericId" : 9,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.009375"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7679093Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest rate parity ratio (forward\/spot)"
      },
      "id" : "o_10",
      "kind" : "OUTPUT",
      "numericId" : 10,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.003715170278638"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7679093Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Notional amount (EUR)"
      },
      "id" : "i_11",
      "kind" : "INPUT",
      "numericId" : 11,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "2500000.00",
      "currency" : "EUR"
    }
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7689338Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Spot rate (EUR\/USD)"
      },
      "id" : "i_12",
      "kind" : "INPUT",
      "numericId" : 12,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "EUR",
      "to" : "USD",
      "rate" : "1.0850"
    }
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7700251Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Notional at spot rate (USD)"
      },
      "id" : "o_13",
      "kind" : "OUTPUT",
      "numericId" : 13,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "2712500.00",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7700251Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Settlement amount at forward rate (USD)"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "2722577.39",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-09-14T06:28:21.7700251Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "basis",
          "value" : "interest-rate-parity forward premium at valuation date"
        } ],
        "name" : "Mark-to-market value vs. spot (USD)"
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "10077.39",
      "currency" : "USD"
    }
  } ],
  "operations" : [ {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-09-14T06:28:21.7679093Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-09-14T06:28:21.7679093Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b",
      "value" : "i_4"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_6"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-09-14T06:28:21.7679093Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-09-14T06:28:21.7679093Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_4"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_7"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-09-14T06:28:21.7679093Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-09-14T06:28:21.7679093Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_5"
    }, {
      "key" : "b",
      "value" : "o_6"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_8"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-09-14T06:28:21.7679093Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-09-14T06:28:21.7679093Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_5"
    }, {
      "key" : "b",
      "value" : "o_7"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_9"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a\/b)mc"
        } ],
        "name" : "divide"
      },
      "finishedAt" : "2026-09-14T06:28:21.7679093Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-09-14T06:28:21.7679093Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_8"
    }, {
      "key" : "b",
      "value" : "o_9"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_10"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-09-14T06:28:21.7700251Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-09-14T06:28:21.7700251Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_11"
    }, {
      "key" : "r",
      "value" : "i_12"
    } ],
    "resultId" : "o_13"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a*f"
        } ],
        "name" : "scale"
      },
      "finishedAt" : "2026-09-14T06:28:21.7700251Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-09-14T06:28:21.7700251Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_13"
    }, {
      "key" : "f",
      "value" : "o_10"
    } ],
    "resultId" : "o_14"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T06:28:21.7700251Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-09-14T06:28:21.7700251Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_14"
    }, {
      "key" : "b",
      "value" : "o_13"
    } ],
    "resultId" : "o_15"
  } ]
}

</CPG>

---

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly. The user message will tell you which of these sets (if any) are relevant to the specific analysis you are asked to perform, and how to use them.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these, e.g. by using a near-duplicate rather than an exact-duplicate identifier or name. The entire reason this analysis is delegated to you rather than a script is to catch what exact-match heuristics cannot; absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_12, i_11, i_1, i_3, i_2, i_5, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_15]
- **Variable IDs consumed as an argument by more than one operation** (`MathContext` variables, which are legitimately reused across operations, are excluded from this list): [i_4, i_5, o_13]
- **Leaf variables that share their exact display name (`descriptor.name`) with another variable elsewhere in the graph**: [] (none detected)

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine `EXPECTED_INVARIANTS` violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and let a human resolve the remaining ambiguity about intent. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical substitution is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if your methodology already extracted a variable's type, unit, or meaning as one thing, you cannot silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use `risk_score` (0–100) to measure the probability and severity of invariant violations, structural tampering, or arithmetic flaws in the graph:
- 0 represents a completely clean graph with zero evidence of tampering or invariant violations.
- 100 represents a confirmed, material attack vector or severe structural exploit.

Use intermediate score ranges to accurately calibrate uncertainty or ambiguous evidence:
- If a finding is clearly real and material, reflect that with a high risk score.
- If you find a genuine irregularity but cannot determine whether it stems from intentional tampering, expected variance, or an unstated domain convention, assign an intermediate risk score — do not erase the ambiguity by defaulting to a low risk score.

---

## RESPONSE FORMAT

Return your audit report strictly as a single valid JSON object containing the fields `risk_score` and `markdown_report`.

Do not wrap the output in any extra text outside the JSON. Ensure all double quotes and newlines inside the `markdown_report` string are properly JSON-escaped (`\"` and `\n`).

### Target JSON Structure:
```json
{
  "risk_score": 50,
  "markdown_report": "Markdown string containing whole report..."
}
```

### Markdown Structure to use inside the "markdown_report" string:

#### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

#### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
