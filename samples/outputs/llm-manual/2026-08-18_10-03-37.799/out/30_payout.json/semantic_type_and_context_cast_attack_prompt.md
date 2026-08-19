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
    "name" : "ETH\/USDC daily options payout"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
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
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Zero (OTM floor)"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH\/USDC spot (2026-06-30)"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4650"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic scale"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "java.math.MathContext"
    },
    "value" : {
      "precision" : 2,
      "roundingMode" : "DOWN"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [0] CALL K=4630"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4630"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [0] CALL K=4630"
      },
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6.863900000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Spot - Strike [0] CALL K=4630"
      },
      "id" : "o_7",
      "kind" : "OUTPUT",
      "numericId" : 7,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [0] CALL K=4630"
      },
      "id" : "o_8",
      "kind" : "OUTPUT",
      "numericId" : 8,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1046924Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [0] CALL K=4630"
      },
      "id" : "o_9",
      "kind" : "OUTPUT",
      "numericId" : 9,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "137.2780000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [1] PUT K=4710"
      },
      "id" : "i_10",
      "kind" : "INPUT",
      "numericId" : 10,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4710"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [1] PUT K=4710"
      },
      "id" : "i_11",
      "kind" : "INPUT",
      "numericId" : 11,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2.843100000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike - Spot [1] PUT K=4710"
      },
      "id" : "o_12",
      "kind" : "OUTPUT",
      "numericId" : 12,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "60"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [1] PUT K=4710"
      },
      "id" : "o_13",
      "kind" : "OUTPUT",
      "numericId" : 13,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "60"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [1] PUT K=4710"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "170.5860000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "170.58"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [2] CALL K=4670"
      },
      "id" : "i_16",
      "kind" : "INPUT",
      "numericId" : 16,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4670"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [2] CALL K=4670"
      },
      "id" : "i_17",
      "kind" : "INPUT",
      "numericId" : 17,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "9.043400000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Spot - Strike [2] CALL K=4670"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-20"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [2] CALL K=4670"
      },
      "id" : "o_19",
      "kind" : "OUTPUT",
      "numericId" : 19,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [2] CALL K=4670"
      },
      "id" : "o_20",
      "kind" : "OUTPUT",
      "numericId" : 20,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0E-18"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_21",
      "kind" : "OUTPUT",
      "numericId" : 21,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [3] PUT K=4550"
      },
      "id" : "i_22",
      "kind" : "INPUT",
      "numericId" : 22,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4550"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [3] PUT K=4550"
      },
      "id" : "i_23",
      "kind" : "INPUT",
      "numericId" : 23,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2.829900000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike - Spot [3] PUT K=4550"
      },
      "id" : "o_24",
      "kind" : "OUTPUT",
      "numericId" : 24,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-100"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [3] PUT K=4550"
      },
      "id" : "o_25",
      "kind" : "OUTPUT",
      "numericId" : 25,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [3] PUT K=4550"
      },
      "id" : "o_26",
      "kind" : "OUTPUT",
      "numericId" : 26,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0E-18"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_27",
      "kind" : "OUTPUT",
      "numericId" : 27,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [4] PUT K=4730"
      },
      "id" : "i_28",
      "kind" : "INPUT",
      "numericId" : 28,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4730"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [4] PUT K=4730"
      },
      "id" : "i_29",
      "kind" : "INPUT",
      "numericId" : 29,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "7.850700000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike - Spot [4] PUT K=4730"
      },
      "id" : "o_30",
      "kind" : "OUTPUT",
      "numericId" : 30,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "80"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [4] PUT K=4730"
      },
      "id" : "o_31",
      "kind" : "OUTPUT",
      "numericId" : 31,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "80"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [4] PUT K=4730"
      },
      "id" : "o_32",
      "kind" : "OUTPUT",
      "numericId" : 32,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "628.0560000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_33",
      "kind" : "OUTPUT",
      "numericId" : 33,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "628.05"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [5] CALL K=4630"
      },
      "id" : "i_34",
      "kind" : "INPUT",
      "numericId" : 34,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4630"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [5] CALL K=4630"
      },
      "id" : "i_35",
      "kind" : "INPUT",
      "numericId" : 35,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4.421300000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Spot - Strike [5] CALL K=4630"
      },
      "id" : "o_36",
      "kind" : "OUTPUT",
      "numericId" : 36,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [5] CALL K=4630"
      },
      "id" : "o_37",
      "kind" : "OUTPUT",
      "numericId" : 37,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [5] CALL K=4630"
      },
      "id" : "o_38",
      "kind" : "OUTPUT",
      "numericId" : 38,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "88.42600000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_39",
      "kind" : "OUTPUT",
      "numericId" : 39,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "88.42"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [6] CALL K=4710"
      },
      "id" : "i_40",
      "kind" : "INPUT",
      "numericId" : 40,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4710"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [6] CALL K=4710"
      },
      "id" : "i_41",
      "kind" : "INPUT",
      "numericId" : 41,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "3.927000000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Spot - Strike [6] CALL K=4710"
      },
      "id" : "o_42",
      "kind" : "OUTPUT",
      "numericId" : 42,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-60"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [6] CALL K=4710"
      },
      "id" : "o_43",
      "kind" : "OUTPUT",
      "numericId" : 43,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [6] CALL K=4710"
      },
      "id" : "o_44",
      "kind" : "OUTPUT",
      "numericId" : 44,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0E-18"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_45",
      "kind" : "OUTPUT",
      "numericId" : 45,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [7] PUT K=4690"
      },
      "id" : "i_46",
      "kind" : "INPUT",
      "numericId" : 46,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4690"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [7] PUT K=4690"
      },
      "id" : "i_47",
      "kind" : "INPUT",
      "numericId" : 47,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5.984100000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike - Spot [7] PUT K=4690"
      },
      "id" : "o_48",
      "kind" : "OUTPUT",
      "numericId" : 48,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "40"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [7] PUT K=4690"
      },
      "id" : "o_49",
      "kind" : "OUTPUT",
      "numericId" : 49,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "40"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [7] PUT K=4690"
      },
      "id" : "o_50",
      "kind" : "OUTPUT",
      "numericId" : 50,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "239.3640000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_51",
      "kind" : "OUTPUT",
      "numericId" : 51,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "239.36"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [8] PUT K=4590"
      },
      "id" : "i_52",
      "kind" : "INPUT",
      "numericId" : 52,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4590"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [8] PUT K=4590"
      },
      "id" : "i_53",
      "kind" : "INPUT",
      "numericId" : 53,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "8.277100000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike - Spot [8] PUT K=4590"
      },
      "id" : "o_54",
      "kind" : "OUTPUT",
      "numericId" : 54,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-60"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [8] PUT K=4590"
      },
      "id" : "o_55",
      "kind" : "OUTPUT",
      "numericId" : 55,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [8] PUT K=4590"
      },
      "id" : "o_56",
      "kind" : "OUTPUT",
      "numericId" : 56,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0E-18"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_57",
      "kind" : "OUTPUT",
      "numericId" : 57,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike [9] PUT K=4550"
      },
      "id" : "i_58",
      "kind" : "INPUT",
      "numericId" : 58,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4550"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Size [9] PUT K=4550"
      },
      "id" : "i_59",
      "kind" : "INPUT",
      "numericId" : 59,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5.915500000000000000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Strike - Spot [9] PUT K=4550"
      },
      "id" : "o_60",
      "kind" : "OUTPUT",
      "numericId" : 60,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-100"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Intrinsic\/ETH [9] PUT K=4550"
      },
      "id" : "o_61",
      "kind" : "OUTPUT",
      "numericId" : 61,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [9] PUT K=4550"
      },
      "id" : "o_62",
      "kind" : "OUTPUT",
      "numericId" : 62,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0E-18"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_63",
      "kind" : "OUTPUT",
      "numericId" : 63,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1056922Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total payout in USDC"
      },
      "id" : "o_64",
      "kind" : "OUTPUT",
      "numericId" : 64,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1263.688000000000"
  } ],
  "operations" : [ {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1046924Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-08-05T08:19:33.1046924Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_5"
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
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1046924Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-08-05T08:19:33.1046924Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_7"
    }, {
      "key" : "b",
      "value" : "i_2"
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
      "finishedAt" : "2026-08-05T08:19:33.1046924Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-08-05T08:19:33.1046924Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_8"
    }, {
      "key" : "b",
      "value" : "i_6"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_10"
    }, {
      "key" : "b",
      "value" : "i_3"
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
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_12"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_13"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_13"
    }, {
      "key" : "b",
      "value" : "i_11"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_14"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_14"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_15"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_16"
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
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_18"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_19"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_19"
    }, {
      "key" : "b",
      "value" : "i_17"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_20"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_20"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_21"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_22"
    }, {
      "key" : "b",
      "value" : "i_3"
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
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_13",
      "numericId" : 13,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_24"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_25"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_14",
      "numericId" : 14,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_25"
    }, {
      "key" : "b",
      "value" : "i_23"
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
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_15",
      "numericId" : 15,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_26"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_27"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_16",
      "numericId" : 16,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_28"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_30"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_17",
      "numericId" : 17,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_30"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_31"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_18",
      "numericId" : 18,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_31"
    }, {
      "key" : "b",
      "value" : "i_29"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_32"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_19",
      "numericId" : 19,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_32"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_33"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_20",
      "numericId" : 20,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_34"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_36"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_21",
      "numericId" : 21,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_36"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_37"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_22",
      "numericId" : 22,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_37"
    }, {
      "key" : "b",
      "value" : "i_35"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_38"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_23",
      "numericId" : 23,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_38"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_39"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_24",
      "numericId" : 24,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_40"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_42"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_25",
      "numericId" : 25,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_42"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_43"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_26",
      "numericId" : 26,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_43"
    }, {
      "key" : "b",
      "value" : "i_41"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_44"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_27",
      "numericId" : 27,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_44"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_45"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_28",
      "numericId" : 28,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_46"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_48"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_29",
      "numericId" : 29,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_48"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_49"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_30",
      "numericId" : 30,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_49"
    }, {
      "key" : "b",
      "value" : "i_47"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_50"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_31",
      "numericId" : 31,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_50"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_51"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_32",
      "numericId" : 32,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_52"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_54"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_33",
      "numericId" : 33,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_54"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_55"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_34",
      "numericId" : 34,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_55"
    }, {
      "key" : "b",
      "value" : "i_53"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_56"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_35",
      "numericId" : 35,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_56"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_57"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_36",
      "numericId" : 36,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_58"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_60"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_37",
      "numericId" : 37,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_60"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_61"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_38",
      "numericId" : 38,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_61"
    }, {
      "key" : "b",
      "value" : "i_59"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_62"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "setScale(a)mc"
        } ],
        "name" : "setScale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_39",
      "numericId" : 39,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_62"
    }, {
      "key" : "mc",
      "value" : "i_4"
    } ],
    "resultId" : "o_63"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:33.1056922Z",
      "id" : "op_40",
      "numericId" : 40,
      "startedAt" : "2026-08-05T08:19:33.1056922Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_9"
    }, {
      "key" : "b0",
      "value" : "o_15"
    }, {
      "key" : "b1",
      "value" : "o_21"
    }, {
      "key" : "b2",
      "value" : "o_27"
    }, {
      "key" : "b3",
      "value" : "o_33"
    }, {
      "key" : "b4",
      "value" : "o_39"
    }, {
      "key" : "b5",
      "value" : "o_45"
    }, {
      "key" : "b6",
      "value" : "o_51"
    }, {
      "key" : "b7",
      "value" : "o_57"
    }, {
      "key" : "b8",
      "value" : "o_63"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_64"
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
