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
    "name" : "E-commerce: order total"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
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
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
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
      "precision" : 2,
      "roundingMode" : "DOWN"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "1.0"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Unit price [SKU-1001]"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "29.99"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Quantity [SKU-1001]"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "3"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Line total [SKU-1001]"
      },
      "id" : "o_6",
      "kind" : "OUTPUT",
      "numericId" : 6,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "89.97"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Unit price [SKU-2002]"
      },
      "id" : "i_7",
      "kind" : "INPUT",
      "numericId" : 7,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "14.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Quantity [SKU-2002]"
      },
      "id" : "i_8",
      "kind" : "INPUT",
      "numericId" : 8,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Line total [SKU-2002]"
      },
      "id" : "o_9",
      "kind" : "OUTPUT",
      "numericId" : 9,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "29.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Unit price [SKU-3003]"
      },
      "id" : "i_10",
      "kind" : "INPUT",
      "numericId" : 10,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "59.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Quantity [SKU-3003]"
      },
      "id" : "i_11",
      "kind" : "INPUT",
      "numericId" : 11,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Line total [SKU-3003]"
      },
      "id" : "o_12",
      "kind" : "OUTPUT",
      "numericId" : 12,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "59.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Unit price [SKU-4004]"
      },
      "id" : "i_13",
      "kind" : "INPUT",
      "numericId" : 13,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "9.99"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Quantity [SKU-4004]"
      },
      "id" : "i_14",
      "kind" : "INPUT",
      "numericId" : 14,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Line total [SKU-4004]"
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "49.95"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Subtotal"
      },
      "id" : "o_16",
      "kind" : "OUTPUT",
      "numericId" : 16,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "227.92"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Discount rate (SAVE10)"
      },
      "id" : "i_17",
      "kind" : "INPUT",
      "numericId" : 17,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.10"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Discount multiplier (SAVE10)"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.90"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Subtotal after SAVE10"
      },
      "id" : "o_19",
      "kind" : "OUTPUT",
      "numericId" : 19,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "205.1280"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Discount rate (LOYALTY5)"
      },
      "id" : "i_20",
      "kind" : "INPUT",
      "numericId" : 20,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.05"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Discount multiplier (LOYALTY5)"
      },
      "id" : "o_21",
      "kind" : "OUTPUT",
      "numericId" : 21,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.95"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Subtotal after LOYALTY5"
      },
      "id" : "o_22",
      "kind" : "OUTPUT",
      "numericId" : 22,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.9E+2"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Shipping fee"
      },
      "id" : "i_23",
      "kind" : "INPUT",
      "numericId" : 23,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "12.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Taxable amount"
      },
      "id" : "o_24",
      "kind" : "OUTPUT",
      "numericId" : 24,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "202.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Tax rate (8%, California)"
      },
      "id" : "i_25",
      "kind" : "INPUT",
      "numericId" : 25,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.08"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Sales tax"
      },
      "id" : "o_26",
      "kind" : "OUTPUT",
      "numericId" : 26,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "16.2000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8617672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Order total"
      },
      "id" : "o_27",
      "kind" : "OUTPUT",
      "numericId" : 27,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "218.7000"
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
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_4"
    }, {
      "key" : "b",
      "value" : "i_5"
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
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_7"
    }, {
      "key" : "b",
      "value" : "i_8"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_10"
    }, {
      "key" : "b",
      "value" : "i_11"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_13"
    }, {
      "key" : "b",
      "value" : "i_14"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_15"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_6"
    }, {
      "key" : "b0",
      "value" : "o_9"
    }, {
      "key" : "b1",
      "value" : "o_12"
    }, {
      "key" : "b2",
      "value" : "o_15"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_16"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_17"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_16"
    }, {
      "key" : "b",
      "value" : "o_18"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_19"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_20"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_21"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_19"
    }, {
      "key" : "b",
      "value" : "o_21"
    }, {
      "key" : "mc",
      "value" : "i_2"
    } ],
    "resultId" : "o_22"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_22"
    }, {
      "key" : "b",
      "value" : "i_23"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_24"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_24"
    }, {
      "key" : "b",
      "value" : "i_25"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_26"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:32.8617672Z",
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-08-05T08:19:32.8617672Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_24"
    }, {
      "key" : "b",
      "value" : "o_26"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_27"
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
