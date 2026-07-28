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
