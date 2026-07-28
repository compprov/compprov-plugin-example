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
    "name" : "Nav calculation example"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8582078Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "origin",
          "value" : "Binance"
        } ],
        "name" : "BTC\/USD rate"
      },
      "id" : "i_1",
      "kind" : "INPUT",
      "numericId" : 1,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "BTC",
      "to" : "USD",
      "rate" : "68989.72"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8622979Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "origin",
          "value" : "Binance"
        } ],
        "name" : "ETH\/USD rate"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USD",
      "rate" : "2083.31"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8622979Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "origin",
          "value" : "Binance"
        } ],
        "name" : "USDC\/USD rate"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "USDC",
      "to" : "USD",
      "rate" : "1.01"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8632981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ],
        "name" : "BTC balance"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "1.24184892",
      "currency" : "BTC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8643003Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ],
        "name" : "ETH balance"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "92.520767008267654318",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8643003Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ],
        "name" : "USDC balance"
      },
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "53344.459319",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8643003Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Stake"
        } ],
        "name" : "ETH balance"
      },
      "id" : "i_7",
      "kind" : "INPUT",
      "numericId" : 7,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "8.310133997912894088",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8643003Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Morpho"
        } ],
        "name" : "USDC balance"
      },
      "id" : "i_8",
      "kind" : "INPUT",
      "numericId" : 8,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "22284.229335",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8648072Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "BTC->USD"
      },
      "id" : "o_9",
      "kind" : "OUTPUT",
      "numericId" : 9,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "85674.80",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8658117Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH(Binance)->USD"
      },
      "id" : "o_10",
      "kind" : "OUTPUT",
      "numericId" : 10,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "192749.43",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8658117Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "USDC(Binance)->USD"
      },
      "id" : "o_11",
      "kind" : "OUTPUT",
      "numericId" : 11,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "53877.90",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8658117Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH(Staked)->USD"
      },
      "id" : "o_12",
      "kind" : "OUTPUT",
      "numericId" : 12,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "17312.58",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8658117Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "USDC(Morpho)->USD"
      },
      "id" : "o_13",
      "kind" : "OUTPUT",
      "numericId" : 13,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "22507.07",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:29:05.8658117Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Assets sum"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "372121.78",
      "currency" : "USD"
    }
  } ],
  "operations" : [ {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:29:05.8648072Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-07-27T09:29:05.8648072Z",
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
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:29:05.8658117Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-07-27T09:29:05.8658117Z",
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
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:29:05.8658117Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-07-27T09:29:05.8658117Z",
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
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:29:05.8658117Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-07-27T09:29:05.8658117Z",
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
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:29:05.8658117Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-07-27T09:29:05.8658117Z",
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
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a+b0+...+bn"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-07-27T09:29:05.8658117Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-07-27T09:29:05.8658117Z",
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
Analyze the computation graph above to detect potential Semantic Type and Context Cast attacks, where technical type safety, signatures, and mathematical replay pass validation perfectly, but the underlying business meaning, domain metadata, or regulatory context of data is covertly altered.

---

## ATTACK VECTOR DEFINITION: Semantic Type and Context Cast Attack
A Semantic Type and Context Cast Attack occurs when an adversary exploits the gap between technical type checking (e.g., confirming a field is a `java.math.BigDecimal`) and semantic domain validation (e.g., confirming whether that `BigDecimal` represents "Gross Revenue" or "Net Profit"). The system maintains 100% technical type continuity and mathematical convergence while silently re-mapping business context — preserving types while quietly altering or stripping domain metadata (`meta.domainType`, `units`, `taxStatus`); passing a variable through an identity or wrapper operation so downstream steps treat it as a completely different domain entity (e.g., casting a "Standard Risk Multiplier" into a "Corporate Discount Factor"); feeding an unadjusted metric into a step that silently consumes it as though already post-adjustment or tax-deducted, without altering the raw numeric value; or otherwise exploiting validation tooling that only checks node connectivity and schema compliance, not business meaning.

Map both technical type attributes (`valueClass`) and business metadata (`descriptor.meta`, units, domain tags) for every variable, and trace the declared business context (C) from root variables through every downstream consumer. A node where C_source != C_target despite identical technical types (`BigDecimal` -> `BigDecimal`) — i.e., where technical type validity and mathematical replay both pass, but semantic continuity does not — is the signature of this attack, regardless of whether the operation performing the cast looks legitimate in isolation.

<EXPECTED_INVARIANTS>
- A variable's business meaning (`descriptor.meta`) must remain consistent across operations unless explicitly transformed by a domain-justified business operation; downstream operations must consume variables whose domain attributes match the operational assumptions of the consuming node.
- Changing the semantic interpretation of a value requires an explicit domain transformation node — implicit re-labeling or metadata suppression is strictly prohibited, and no operation may consume a variable under a business definition that conflicts with its originating metadata.
- Domain transitions (e.g., Gross -> Net, Local Currency -> Base Currency) must be backed by explicit, auditable transformation logic, not merely by an operation that happens to be type-safe.
</EXPECTED_INVARIANTS>

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine invariant violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and note the remaining ambiguity about intent for the reader to resolve. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical re-mapping is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if you already extracted a variable's type or business meaning as one thing, your verdict can't silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use your confidence score to carry calibration, rather than resolving it by force-fitting the verdict. If a finding is clearly real and material, say so with a high score. If you found something genuinely irregular but aren't sure it rises to tampering rather than expected variance or a legitimate design you can't fully rule out, report that assessment and reflect the doubt in a lower confidence score — don't make the uncertainty disappear by defaulting the verdict to CLEAN instead.

---

## REQUIRED OUTPUT FORMAT

Return your audit report as markdown, using this structure exactly:

### Verdict
One of: `CLEAN` | `SEMANTIC CAST DETECTED` | `CONTEXT MISMATCH`

### Confidence Score
A number from 0-100.

### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
