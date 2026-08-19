## ROLE
You are a Principal Computational Provenance Auditor and Security Engineer, examining Directed Acyclic Graphs (DAGs) that record computational execution traces across financial, scientific, and engineering pipelines. You assume a competent adversary who designs tampering specifically to pass local mathematical replay and survive casual review — not a naive one who leaves obvious errors. The specific attack vector to focus this audit on is defined in the user message below.

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

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly. The user message will tell you which of these sets (if any) are relevant to the specific analysis you are asked to perform, and how to use them.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these, e.g. by using a near-duplicate rather than an exact-duplicate identifier or name. The entire reason this analysis is delegated to you rather than a script is to catch what exact-match heuristics cannot; absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_7, i_6, i_8, i_1, i_3, i_2, i_5, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_14]
- **Variable IDs consumed as an argument by more than one operation** (`MathContext` variables, which are legitimately reused across operations, are excluded from this list): [i_2, i_3]
- **Leaf variables that share their exact display name (`descriptor.name`) with another variable elsewhere in the graph**: [] (none detected)

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine `EXPECTED_INVARIANTS` violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and let a human resolve the remaining ambiguity about intent. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical substitution is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if your methodology already extracted a variable's type, unit, or meaning as one thing, your verdict can't silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use `confidence_score` to carry calibration, rather than resolving it by force-fitting the verdict. If a finding is clearly real and material, say so with a high score. If you found something genuinely irregular but aren't sure it rises to tampering rather than expected variance or a legitimate design you can't fully rule out, report that assessment and reflect the doubt in a lower confidence_score — don't make the uncertainty disappear by defaulting the verdict to CLEAN instead.

---

## RESPONSE FORMAT

Return your audit report strictly as a single valid JSON object containing the fields `verdict`, `confidence_score`, and `markdown_report`.

Do not wrap the output in any extra text outside the JSON. Ensure all double quotes and newlines inside the `markdown_report` string are properly JSON-escaped (`\"` and `\n`).

### Target JSON Structure:
```json
{
  "verdict": "CLEAN",
  "confidence_score": 95,
  "markdown_report": "Markdown string containing whole report..."
}
```

- `confidence_score` value lies between 0 and 100.
- `verdict` must be exactly one of the values listed in the user message's `<VERDICT>` array — no other value is valid.

### Markdown Structure to use inside the "markdown_report" string:

#### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

#### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
