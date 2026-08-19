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
    "meta" : [ ],
    "name" : "Cost basis: weighted-average across 3 lots, partial sale"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
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
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Computation precision (tampered)"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "java.math.MathContext"
    },
    "value" : {
      "precision" : 4,
      "roundingMode" : "DOWN"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 1 shares"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "100"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 1 price per share"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "42.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 1 cost"
      },
      "id" : "o_5",
      "kind" : "OUTPUT",
      "numericId" : 5,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4250.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 2 shares"
      },
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "150"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 2 price per share"
      },
      "id" : "i_7",
      "kind" : "INPUT",
      "numericId" : 7,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "38.25"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 2 cost"
      },
      "id" : "o_8",
      "kind" : "OUTPUT",
      "numericId" : 8,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5737.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 3 shares"
      },
      "id" : "i_9",
      "kind" : "INPUT",
      "numericId" : 9,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "75"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 3 price per share"
      },
      "id" : "i_10",
      "kind" : "INPUT",
      "numericId" : 10,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "51.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Lot 3 cost"
      },
      "id" : "o_11",
      "kind" : "OUTPUT",
      "numericId" : 11,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "3825.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total shares held"
      },
      "id" : "o_12",
      "kind" : "OUTPUT",
      "numericId" : 12,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "325"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total cost"
      },
      "id" : "o_13",
      "kind" : "OUTPUT",
      "numericId" : 13,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "13812.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Weighted-average cost per share"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "42.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Shares sold"
      },
      "id" : "i_15",
      "kind" : "INPUT",
      "numericId" : 15,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "120"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Sale price per share"
      },
      "id" : "i_16",
      "kind" : "INPUT",
      "numericId" : 16,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "47.75"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Sale proceeds"
      },
      "id" : "o_17",
      "kind" : "OUTPUT",
      "numericId" : 17,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5730.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Cost basis of shares sold"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5100.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.7977389Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Realized gain\/loss"
      },
      "id" : "o_19",
      "kind" : "OUTPUT",
      "numericId" : 19,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "630.00"
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
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
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
    "resultId" : "o_5"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_6"
    }, {
      "key" : "b",
      "value" : "i_7"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_9"
    }, {
      "key" : "b",
      "value" : "i_10"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_11"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b0",
      "value" : "i_6"
    }, {
      "key" : "b1",
      "value" : "i_9"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_12"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_5"
    }, {
      "key" : "b0",
      "value" : "o_8"
    }, {
      "key" : "b1",
      "value" : "o_11"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_13"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a\/b)mc"
        } ],
        "name" : "divide"
      },
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_13"
    }, {
      "key" : "b",
      "value" : "o_12"
    }, {
      "key" : "mc",
      "value" : "i_2"
    } ],
    "resultId" : "o_14"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_15"
    }, {
      "key" : "b",
      "value" : "i_16"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_17"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_15"
    }, {
      "key" : "b",
      "value" : "o_14"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_18"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:32.7977389Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-08-05T08:19:32.7977389Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_17"
    }, {
      "key" : "b",
      "value" : "o_18"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_19"
  } ]
}

</CPG>

---

## OBJECTIVE
Analyze the computation graph above to detect potential Calculation Omission attacks, where mandatory state changes—including both debit/cost entries (e.g., tax liabilities, operational costs) and credit/revenue entries (e.g., bonuses, profit windfalls, subsidies)—are calculated correctly in isolated subgraphs but intentionally severed or ignored during final net aggregation.

---

## ATTACK VECTOR DEFINITION: Calculation Omission
Calculation Omission occurs when a mandatory adjustment — a cost, credit, correction, or cross-check — is computed or measured correctly and transparently within the graph, but silently excluded from the final result the pipeline reports, biasing that result toward a more favorable or simpler outcome than a complete computation would produce. This is domain-agnostic, not limited to money: it applies equally to an excluded tax liability, a skipped correction factor in an engineering simulation, or an unused cross-validation measurement in a metrology pipeline. It typically shows up as a correctly-computed or correctly-measured variable left as an unconsumed dead-end, or a final aggregation/result operation whose arguments quietly omit a component that its own name, role, and metadata indicate it should include.

<EXPECTED_INVARIANTS>
- Every variable whose name, role, or metadata identifies it as a mandatory adjustment, deduction, credit, correction, or cross-check for the pipeline's final reported result — in any domain, not only financial — must have an active causal path into the operation that produces that result.
- Reconstruct the complete formula or computation the final result *should* reflect from every such qualifying variable, and compare it against what the final aggregation/result operation *actually* consumes; any qualifying variable present in the former but missing from the latter is the omission.
- No variable meeting that description may exist as an unconsumed dead-end (leaf) while the pipeline reports its result as though the computation were complete.
</EXPECTED_INVARIANTS>

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine invariant violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and note the remaining ambiguity about intent for the reader to resolve. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical omission is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if you already extracted a variable's role or meaning as one thing, your verdict can't silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use your confidence score to carry calibration, rather than resolving it by force-fitting the verdict. If a finding is clearly real and material, say so with a high score. If you found something genuinely irregular but aren't sure it rises to tampering rather than expected variance or a legitimate design you can't fully rule out, report that assessment and reflect the doubt in a lower confidence score — don't make the uncertainty disappear by defaulting the verdict to CLEAN instead.

---

## REQUIRED OUTPUT FORMAT

Return your audit report as markdown, using this structure exactly:

### Verdict
One of: `CLEAN` | `CALCULATION OMISSION DETECTED` | `UNLINKED DEDUCTION`

### Confidence Score
A number from 0-100.

### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
