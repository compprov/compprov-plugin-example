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
        "WSTETH" : 18
      }
    } ],
    "name" : "Nav calculation example"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
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
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
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
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "origin",
          "value" : "Binance"
        } ],
        "name" : "WSTETH\/ETH rate"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "WSTETH",
      "to" : "ETH",
      "rate" : "1.243492"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "origin",
          "value" : "Binance"
        } ],
        "name" : "USDC\/USD rate"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "USDC",
      "to" : "USD",
      "rate" : "1.01"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ],
        "name" : "BTC balance"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "5.02765241",
      "currency" : "BTC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ],
        "name" : "ETH balance"
      },
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "9.153983152549328039",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Binance"
        } ],
        "name" : "USDC balance"
      },
      "id" : "i_7",
      "kind" : "INPUT",
      "numericId" : 7,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "91214.350627",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Trust Nodes"
        } ],
        "name" : "ETH balance"
      },
      "id" : "i_8",
      "kind" : "INPUT",
      "numericId" : 8,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "72.327829715473001215",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0891745Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Lido"
        } ],
        "name" : "WSTETH balance"
      },
      "id" : "i_9",
      "kind" : "INPUT",
      "numericId" : 9,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "9.170773527744636055",
      "currency" : "WSTETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "Morpho"
        } ],
        "name" : "USDC balance"
      },
      "id" : "i_10",
      "kind" : "INPUT",
      "numericId" : 10,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "26064.398971",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "WSTETH balance"
      },
      "id" : "o_11",
      "kind" : "OUTPUT",
      "numericId" : 11,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "81.498603243217637270",
      "currency" : "WSTETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "WSTETH->ETH"
      },
      "id" : "o_12",
      "kind" : "OUTPUT",
      "numericId" : 12,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "101.342861144115186204",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "BTC->USD"
      },
      "id" : "o_13",
      "kind" : "OUTPUT",
      "numericId" : 13,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "346856.33",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH(Binance)->USD"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "19070.58",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "USDC(Binance)->USD"
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "92126.49",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH(Staked)->USD"
      },
      "id" : "o_16",
      "kind" : "OUTPUT",
      "numericId" : 16,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "211128.59",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "USDC(Morpho)->USD"
      },
      "id" : "o_17",
      "kind" : "OUTPUT",
      "numericId" : 17,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "26325.04",
      "currency" : "USD"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.0901812Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Assets sum"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "695507.03",
      "currency" : "USD"
    }
  } ],
  "operations" : [ {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a+b"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.0901812Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-08-05T08:19:33.0901812Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_9"
    }, {
      "key" : "b",
      "value" : "i_8"
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
      "finishedAt" : "2026-08-05T08:19:33.0901812Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-08-05T08:19:33.0901812Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_11"
    }, {
      "key" : "r",
      "value" : "i_3"
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
      "finishedAt" : "2026-08-05T08:19:33.0901812Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-08-05T08:19:33.0901812Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_5"
    }, {
      "key" : "r",
      "value" : "i_1"
    } ],
    "resultId" : "o_13"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-08-05T08:19:33.0901812Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-08-05T08:19:33.0901812Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_6"
    }, {
      "key" : "r",
      "value" : "i_2"
    } ],
    "resultId" : "o_14"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-08-05T08:19:33.0901812Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-08-05T08:19:33.0901812Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_7"
    }, {
      "key" : "r",
      "value" : "i_4"
    } ],
    "resultId" : "o_15"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-08-05T08:19:33.0901812Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-08-05T08:19:33.0901812Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_12"
    }, {
      "key" : "r",
      "value" : "i_2"
    } ],
    "resultId" : "o_16"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-08-05T08:19:33.0901812Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-08-05T08:19:33.0901812Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_10"
    }, {
      "key" : "r",
      "value" : "i_4"
    } ],
    "resultId" : "o_17"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a+b0+...+bn"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:33.0901812Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-08-05T08:19:33.0901812Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_13"
    }, {
      "key" : "b0",
      "value" : "o_14"
    }, {
      "key" : "b1",
      "value" : "o_15"
    }, {
      "key" : "b2",
      "value" : "o_16"
    }, {
      "key" : "b3",
      "value" : "o_17"
    } ],
    "resultId" : "o_18"
  } ]
}

</CPG>

---

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these. Absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_7, i_6, i_9, i_8, i_10, i_1, i_3, i_2, i_5, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_18]
- **Variable IDs consumed as an argument by more than one operation** (`MathContext` variables, which are legitimately reused across operations, are excluded from this list): [i_2, i_4]
- **Leaf variables that share their exact display name (`descriptor.name`) with another variable elsewhere in the graph**: [] (none detected)

---

## OBJECTIVE
Analyze the computation graph above to detect potential Topological Accumulation Fraud via Double Counting attacks, where valid, origin entities are fed into aggregation nodes via duplicate or parallel causal paths to artificially manipulate consolidated financial metrics—either inflating reported revenues/assets or deflating taxable income/liabilities.

---

## ATTACK VECTOR DEFINITION: Topological Accumulation Fraud via Double Counting
Topological Accumulation Fraud occurs when an adversary exploits the scale and complexity of a computational graph to reuse the same real, legitimate entity — an inflow/revenue value **or a cost/deduction value** — across multiple execution paths that both ultimately affect the same downstream financial result. This is the identical fraud shape whether the duplicated entity adds to a total twice (inflating it) or subtracts from a total twice (deflating it); do not treat one direction as more suspicious than the other. Common mechanisms: mapping a single source variable into both a primary calculation path and a secondary, look-alike path that both feed the same rollup; routing a single entity through multiple distinct intermediate operations before aggregating them; concealing the reuse inside a dense, high-node-count topology where local replay confirms each node independently but misses the global overlap; re-wrapping a variable through an identity/passthrough operation to assign it a new `track.id` while retaining the same underlying reference; or — the deduction-side mirror of the above — netting a cost out of one branch's intermediate subtotal, then subtracting that *same* cost again from a later aggregate that already incorporates that subtotal.

Trace all directed paths from each root input forward to the graph's *true terminal output* — do not stop at the first aggregation/rollup operation encountered. An intermediate subtotal can itself be consumed again by a later aggregation or adjustment step, and reuse at that later step is just as much a duplication as reuse at the first one. Count the path multiplicity $M(V_{in}, Op_{agg})$ all the way to the terminal output, whether the value contributes there as a positive addend or a subtracted deduction — multiplicity greater than 1 without an explicit split/allocation rule is the anomaly, regardless of direction.

---

## USING THE STRUCTURAL REFERENCE DATA
Of the sets above, the **variable IDs consumed as an argument by more than one operation** are relevant to this task — use them to focus the path-multiplicity analysis above. This is a structural fact, not a verdict; reuse alone does not by itself indicate fraud. For each ID, determine whether the reuse is a legitimate shared parameter/allocation or genuine double-counting into the same aggregation node — and continue checking for duplication this list would not catch, such as the same underlying entity re-entered under a different variable ID (Origin ID / Hash Aliasing).

<EXPECTED_INVARIANTS>
- Path multiplicity $M(V_{in}, Op_{agg})$ for any financial input entity into the terminal output — whether contributing as an addend or as a deduction — must equal $1$. A single root financial asset, transaction entity, or cost/deduction must not contribute its value multiple times to an additive or subtractive rollup unless explicit, auditable proportional splitting logic is documented.
- Deduplicated sum $S_{dedup}$ (each unique root entity counted once) must match the reported consolidation $S_{reported}$.
- Re-wrapping or cloning an intermediate variable does not grant it unique entity status if its lineage traces back to a previously consumed root entity; intermediate alias nodes must preserve original entity tracking metadata to prevent duplicate path masking.
- A cost, fee, or deduction that has already been netted into an intermediate subtotal must not be subtracted again from a later aggregate that already incorporates that subtotal.
</EXPECTED_INVARIANTS>

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine invariant violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and note the remaining ambiguity about intent for the reader to resolve. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical duplication is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if you already extracted an entity's identity or role as one thing, your verdict can't silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use your confidence score to carry calibration, rather than resolving it by force-fitting the verdict. If a finding is clearly real and material, say so with a high score. If you found something genuinely irregular but aren't sure it rises to tampering rather than a legitimate shared allocation you can't fully rule out, report that assessment and reflect the doubt in a lower confidence score — don't make the uncertainty disappear by defaulting the verdict to CLEAN instead.

---

## REQUIRED OUTPUT FORMAT

Return your audit report as markdown, using this structure exactly:

### Verdict
One of: `CLEAN` | `DOUBLE COUNTING DETECTED` | `TOPOLOGICAL ANOMALY`

### Confidence Score
A number from 0-100.

### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
