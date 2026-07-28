## ROLE
You are a Principal Computational Provenance Auditor and Security Engineer, examining Directed Acyclic Graphs (DAGs) that record computational execution traces across financial, scientific, and engineering pipelines. You assume a competent adversary who designs tampering specifically to pass local mathematical replay and survive casual review — not a naive one who leaves obvious errors.

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
    "name" : "Nav calculation example",
    "meta" : [ ]
  },
  "variables" : [ {
    "track" : {
      "id" : "i_1",
      "numericId" : 1,
      "createdAt" : "2026-04-27T17:45:16.1984957Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "BTC/USD rate",
        "meta" : [ {
          "key" : "origin",
          "value" : "Binance"
        } ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "BTC",
      "to" : "USD",
      "rate" : "68989.72"
    }
  }, {
    "track" : {
      "id" : "i_2",
      "numericId" : 2,
      "createdAt" : "2026-04-27T17:45:16.2014948Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "ETH/USD rate",
        "meta" : [ {
          "key" : "origin",
          "value" : "Binance"
        } ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USD",
      "rate" : "2083.31"
    }
  }, {
    "track" : {
      "id" : "i_3",
      "numericId" : 3,
      "createdAt" : "2026-04-27T17:45:16.2014948Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "USDC/USD rate",
        "meta" : [ {
          "key" : "origin",
          "value" : "Binance"
        } ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "USDC",
      "to" : "USD",
      "rate" : "1.01"
    }
  }, {
    "track" : {
      "id" : "i_4",
      "numericId" : 4,
      "createdAt" : "2026-04-27T17:45:16.2014948Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "BTC balance",
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "BTC",
      "amount" : "2.13000000"
    }
  }, {
    "track" : {
      "id" : "i_5",
      "numericId" : 5,
      "createdAt" : "2026-04-27T17:45:16.2024965Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "ETH balance",
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "ETH",
      "amount" : "23.340000000000000000"
    }
  }, {
    "track" : {
      "id" : "i_6",
      "numericId" : 6,
      "createdAt" : "2026-04-27T17:45:16.2024965Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "USDC balance",
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "USDC",
      "amount" : "532.900000"
    }
  }, {
    "track" : {
      "id" : "i_7",
      "numericId" : 7,
      "createdAt" : "2026-04-27T17:45:16.2024965Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "ETH balance",
        "meta" : [ {
          "key" : "source",
          "value" : "Stake"
        } ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "ETH",
      "amount" : "5.910000000000000000"
    }
  }, {
    "track" : {
      "id" : "i_8",
      "numericId" : 8,
      "createdAt" : "2026-04-27T17:45:16.2024965Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "USDC balance",
        "meta" : [ {
          "key" : "source",
          "value" : "Morpho"
        } ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "USDC",
      "amount" : "221114.900000"
    }
  }, {
    "track" : {
      "id" : "o_9",
      "numericId" : 9,
      "createdAt" : "2026-04-27T17:45:16.2034954Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "BTC->USD",
        "meta" : [ ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "USD",
      "amount" : "146948.10"
    }
  }, {
    "track" : {
      "id" : "o_10",
      "numericId" : 10,
      "createdAt" : "2026-04-27T17:45:16.2044967Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "ETH(Binance)->USD",
        "meta" : [ ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "USD",
      "amount" : "48624.45"
    }
  }, {
    "track" : {
      "id" : "o_11",
      "numericId" : 11,
      "createdAt" : "2026-04-27T17:45:16.2044967Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "USDC(Binance)->USD",
        "meta" : [ ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "USD",
      "amount" : "538.22"
    }
  }, {
    "track" : {
      "id" : "o_12",
      "numericId" : 12,
      "createdAt" : "2026-04-27T17:45:16.2054954Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "ETH(Staked)->USD",
        "meta" : [ ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "USD",
      "amount" : "12312.36"
    }
  }, {
    "track" : {
      "id" : "o_13",
      "numericId" : 13,
      "createdAt" : "2026-04-27T17:45:16.2054954Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "USDC(Morpho)->USD",
        "meta" : [ ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "USD",
      "amount" : "223326.04"
    }
  }, {
    "track" : {
      "id" : "o_14",
      "numericId" : 14,
      "createdAt" : "2026-04-27T17:45:16.207497Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Assets sum",
        "meta" : [ ]
      },
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "currency" : "USD",
      "amount" : "441749.17"
    }
  } ],
  "operations" : [ {
    "track" : {
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-04-27T17:45:16.2024965Z",
      "finishedAt" : "2026-04-27T17:45:16.2034954Z",
      "descriptor" : {
        "name" : "convert",
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ]
      },
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_4"
    }, {
      "key" : "r",
      "value" : "i_1"
    } ],
    "resultId" : "o_9"
  }, {
    "track" : {
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-04-27T17:45:16.2044967Z",
      "finishedAt" : "2026-04-27T17:45:16.2044967Z",
      "descriptor" : {
        "name" : "convert",
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ]
      },
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_5"
    }, {
      "key" : "r",
      "value" : "i_2"
    } ],
    "resultId" : "o_10"
  }, {
    "track" : {
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-04-27T17:45:16.2044967Z",
      "finishedAt" : "2026-04-27T17:45:16.2044967Z",
      "descriptor" : {
        "name" : "convert",
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ]
      },
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_6"
    }, {
      "key" : "r",
      "value" : "i_3"
    } ],
    "resultId" : "o_11"
  }, {
    "track" : {
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-04-27T17:45:16.2054954Z",
      "finishedAt" : "2026-04-27T17:45:16.2054954Z",
      "descriptor" : {
        "name" : "convert",
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ]
      },
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_7"
    }, {
      "key" : "r",
      "value" : "i_2"
    } ],
    "resultId" : "o_12"
  }, {
    "track" : {
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-04-27T17:45:16.2054954Z",
      "finishedAt" : "2026-04-27T17:45:16.2054954Z",
      "descriptor" : {
        "name" : "convert",
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ]
      },
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_8"
    }, {
      "key" : "r",
      "value" : "i_3"
    } ],
    "resultId" : "o_13"
  }, {
    "track" : {
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-04-27T17:45:16.207497Z",
      "finishedAt" : "2026-04-27T17:45:16.207497Z",
      "descriptor" : {
        "name" : "addBulk",
        "meta" : [ {
          "key" : "formula",
          "value" : "a+b0+...+bn"
        } ]
      },
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_9"
    }, {
      "key" : "b0",
      "value" : "o_10"
    }, {
      "key" : "b1",
      "value" : "o_11"
    }, {
      "key" : "b2",
      "value" : "o_12"
    }, {
      "key" : "b3",
      "value" : "o_13"
    } ],
    "resultId" : "o_14"
  } ]
}

</CPG>

---

## OBJECTIVE
Analyze the computation graph above to detect potential Precision and Scale Tampering attacks or subtle arithmetic logic flaws.

---

## ATTACK VECTOR DEFINITION: Precision and Scale Tampering
Precision and Scale Tampering occurs when an adversary or faulty business logic exploits the way numbers are scaled, rounded, or converted between data types/units — forcibly reducing an intermediate value's scale before completing an aggregation, causing residual value leakage; using a non-standard rounding mode (`ROUND_DOWN`/`FLOOR` instead of `HALF_EVEN`) to consistently drain sub-cent fractions into an attacker-controlled pool (Salami Slicing); mixing scales or token units (e.g., 6-decimal USDC with 18-decimal WEI/wstETH) without a proper scaling transformation; or downcasting a precise decimal type to `float`/`double`/integer mid-pipeline, degrading precision.

Recompute every operation with infinite-precision rational arithmetic and compare against the reported value at each step ($\Delta = |Exact\_Result - Reported\_Result|$). A boundary-sized discrepancy alone is not sufficient for an anomaly verdict — see the invariants below for what actually distinguishes tampering from ordinary rounding.

<EXPECTED_INVARIANTS>
- Asset conservation must hold across all intermediate steps, at a materiality threshold appropriate to the values involved — not at the level of single-ULP rounding noise on isolated operations. A single rounding-convention difference (truncate vs. round-to-nearest) can never differ from the alternative by more than 1 unit at the target scale; that is the mathematical ceiling of "wrong rounding mode on one operation," not evidence of tampering by itself.
- Rounding mode must default to HALF_EVEN / HALF_UP unless explicitly bounded. When an operation carries an explicit `mc` (MathContext) argument, check it directly — a result consistent with that declared context is not a violation regardless of what a different default would have produced. When no MathContext argument exists at all (common for some domain value-wrapper types), the graph provides no ground truth for "the correct" convention, so an isolated 1-unit discrepancy is not a confirmed violation on its own.
- Genuine Salami Slicing requires consistency across a *scalable* operation population — one whose count can grow with users, transactions, or volume, so that many negligible individual skims accumulate into something material — and demonstrated accumulation toward an identifiable beneficiary or sink, not mere directional repetition. A bias repeated only across a small, structurally bounded population (e.g., one conversion per asset held in a portfolio/NAV calculation, capped by composition rather than volume) does not qualify, however consistent the direction, because there is no way to "run it more times" to extract more value. Likewise, if deltas from multiple flagged operations cancel out in the final reported result rather than accumulating, that is evidence against a beneficiary-driven exploit, not a more sophisticated version of one.
- Scale conversions between units must strictly preserve the source asset's native precision (e.g., 18 decimals for WEI/wstETH, 6 for USDC) and maintain exact arbitrary-precision representations without unhandled intermediate truncations.
</EXPECTED_INVARIANTS>

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine invariant violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and note the remaining ambiguity about intent for the reader to resolve. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical manipulation is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if you already extracted a value or scale as one thing, your verdict can't silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use your confidence score to carry calibration, rather than resolving it by force-fitting the verdict. If a finding is clearly real and material, say so with a high score. If you found something genuinely irregular but aren't sure it rises to tampering rather than expected variance or a legitimate design you can't fully rule out, report that assessment and reflect the doubt in a lower confidence score — don't make the uncertainty disappear by defaulting the verdict to CLEAN instead.

---

## REQUIRED OUTPUT FORMAT

Return your audit report as markdown, using this structure exactly:

### Verdict
One of: `CLEAN` | `ANOMALY DETECTED` | `SUSPICIOUS LOGIC`

### Confidence Score
A number from 0-100.

### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
