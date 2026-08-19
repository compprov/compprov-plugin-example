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
    "name" : "ETH/USDC daily options payout",
    "meta" : [ ]
  },
  "variables" : [ {
    "track" : {
      "id" : "i_1",
      "numericId" : 1,
      "createdAt" : "2026-06-30T08:09:07.1497588Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Computation precision (DECIMAL64)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.MathContext"
    },
    "value" : {
      "precision" : 16,
      "roundingMode" : "HALF_EVEN"
    }
  }, {
    "track" : {
      "id" : "i_2",
      "numericId" : 2,
      "createdAt" : "2026-06-30T08:09:07.1517591Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Zero (OTM floor)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "id" : "i_3",
      "numericId" : 3,
      "createdAt" : "2026-06-30T08:11:07.1537587Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "ETH/USDC spot (2026-06-30)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4650"
  }, {
    "track" : {
      "id" : "i_4",
      "numericId" : 4,
      "createdAt" : "2026-06-30T08:09:07.1572008Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [0] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4630"
  }, {
    "track" : {
      "id" : "i_5",
      "numericId" : 5,
      "createdAt" : "2026-06-30T08:09:07.1572008Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [0] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6.8639"
  }, {
    "track" : {
      "id" : "o_6",
      "numericId" : 6,
      "createdAt" : "2026-06-30T08:09:07.1572008Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Spot - Strike [0] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20"
  }, {
    "track" : {
      "id" : "o_7",
      "numericId" : 7,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [0] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20"
  }, {
    "track" : {
      "id" : "o_8",
      "numericId" : 8,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [0] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "137.2780"
  }, {
    "track" : {
      "id" : "i_9",
      "numericId" : 9,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [1] PUT K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4710"
  }, {
    "track" : {
      "id" : "i_10",
      "numericId" : 10,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [1] PUT K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2.8431"
  }, {
    "track" : {
      "id" : "o_11",
      "numericId" : 11,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Strike - Spot [1] PUT K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "60"
  }, {
    "track" : {
      "id" : "o_12",
      "numericId" : 12,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [1] PUT K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "60"
  }, {
    "track" : {
      "id" : "o_13",
      "numericId" : 13,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [1] PUT K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "170.5860"
  }, {
    "track" : {
      "id" : "i_14",
      "numericId" : 14,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [2] CALL K=4670",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4670"
  }, {
    "track" : {
      "id" : "i_15",
      "numericId" : 15,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [2] CALL K=4670",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "9.0434"
  }, {
    "track" : {
      "id" : "o_16",
      "numericId" : 16,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Spot - Strike [2] CALL K=4670",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-20"
  }, {
    "track" : {
      "id" : "o_17",
      "numericId" : 17,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [2] CALL K=4670",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "id" : "o_18",
      "numericId" : 18,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [2] CALL K=4670",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0000"
  }, {
    "track" : {
      "id" : "i_19",
      "numericId" : 19,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [3] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4550"
  }, {
    "track" : {
      "id" : "i_20",
      "numericId" : 20,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [3] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2.8299"
  }, {
    "track" : {
      "id" : "o_21",
      "numericId" : 21,
      "createdAt" : "2026-06-30T08:09:07.1592823Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Strike - Spot [3] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-100"
  }, {
    "track" : {
      "id" : "o_22",
      "numericId" : 22,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [3] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "id" : "o_23",
      "numericId" : 23,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [3] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0000"
  }, {
    "track" : {
      "id" : "i_24",
      "numericId" : 24,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [4] PUT K=4730",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4730"
  }, {
    "track" : {
      "id" : "i_25",
      "numericId" : 25,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [4] PUT K=4730",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "7.8507"
  }, {
    "track" : {
      "id" : "o_26",
      "numericId" : 26,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Strike - Spot [4] PUT K=4730",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "80"
  }, {
    "track" : {
      "id" : "o_27",
      "numericId" : 27,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [4] PUT K=4730",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "80"
  }, {
    "track" : {
      "id" : "o_28",
      "numericId" : 28,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [4] PUT K=4730",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "628.0560"
  }, {
    "track" : {
      "id" : "i_29",
      "numericId" : 29,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [5] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4630"
  }, {
    "track" : {
      "id" : "i_30",
      "numericId" : 30,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [5] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4.4213"
  }, {
    "track" : {
      "id" : "o_31",
      "numericId" : 31,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Spot - Strike [5] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20"
  }, {
    "track" : {
      "id" : "o_32",
      "numericId" : 32,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [5] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20"
  }, {
    "track" : {
      "id" : "o_33",
      "numericId" : 33,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [5] CALL K=4630",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "88.4260"
  }, {
    "track" : {
      "id" : "i_34",
      "numericId" : 34,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [6] CALL K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4710"
  }, {
    "track" : {
      "id" : "i_35",
      "numericId" : 35,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [6] CALL K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "3.9270"
  }, {
    "track" : {
      "id" : "o_36",
      "numericId" : 36,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Spot - Strike [6] CALL K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-60"
  }, {
    "track" : {
      "id" : "o_37",
      "numericId" : 37,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [6] CALL K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "id" : "o_38",
      "numericId" : 38,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [6] CALL K=4710",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0000"
  }, {
    "track" : {
      "id" : "i_39",
      "numericId" : 39,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [7] PUT K=4690",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4690"
  }, {
    "track" : {
      "id" : "i_40",
      "numericId" : 40,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [7] PUT K=4690",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5.9841"
  }, {
    "track" : {
      "id" : "o_41",
      "numericId" : 41,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Strike - Spot [7] PUT K=4690",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "40"
  }, {
    "track" : {
      "id" : "o_42",
      "numericId" : 42,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [7] PUT K=4690",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "40"
  }, {
    "track" : {
      "id" : "o_43",
      "numericId" : 43,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [7] PUT K=4690",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "239.3640"
  }, {
    "track" : {
      "id" : "i_44",
      "numericId" : 44,
      "createdAt" : "2026-06-30T08:09:07.1599854Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [8] PUT K=4590",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4590"
  }, {
    "track" : {
      "id" : "i_45",
      "numericId" : 45,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [8] PUT K=4590",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "8.2771"
  }, {
    "track" : {
      "id" : "o_46",
      "numericId" : 46,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Strike - Spot [8] PUT K=4590",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-60"
  }, {
    "track" : {
      "id" : "o_47",
      "numericId" : 47,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [8] PUT K=4590",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "id" : "o_48",
      "numericId" : 48,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [8] PUT K=4590",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0000"
  }, {
    "track" : {
      "id" : "i_49",
      "numericId" : 49,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Strike [9] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4550"
  }, {
    "track" : {
      "id" : "i_50",
      "numericId" : 50,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Size [9] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5.9155"
  }, {
    "track" : {
      "id" : "o_51",
      "numericId" : 51,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Strike - Spot [9] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-100"
  }, {
    "track" : {
      "id" : "o_52",
      "numericId" : 52,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Intrinsic/ETH [9] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "id" : "o_53",
      "numericId" : 53,
      "createdAt" : "2026-06-30T08:09:07.1612797Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Payout [9] PUT K=4550",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0000"
  }, {
    "track" : {
      "id" : "o_54",
      "numericId" : 54,
      "createdAt" : "2026-06-30T08:09:07.1632811Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Total payout in USDC",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1263.7100"
  } ],
  "operations" : [ {
    "track" : {
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-06-30T08:09:07.1572008Z",
      "finishedAt" : "2026-06-30T08:09:07.1572008Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
    "resultId" : "o_6"
  }, {
    "track" : {
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-06-30T08:09:07.1582009Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_6"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_7"
  }, {
    "track" : {
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_7"
    }, {
      "key" : "b",
      "value" : "i_5"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_8"
  }, {
    "track" : {
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_9"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_11"
  }, {
    "track" : {
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_11"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_12"
  }, {
    "track" : {
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_12"
    }, {
      "key" : "b",
      "value" : "i_10"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_13"
  }, {
    "track" : {
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_14"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_16"
  }, {
    "track" : {
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_16"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_17"
  }, {
    "track" : {
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_17"
    }, {
      "key" : "b",
      "value" : "i_15"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_18"
  }, {
    "track" : {
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1592823Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_19"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_21"
  }, {
    "track" : {
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-06-30T08:09:07.1592823Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_21"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_22"
  }, {
    "track" : {
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_22"
    }, {
      "key" : "b",
      "value" : "i_20"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_23"
  }, {
    "track" : {
      "id" : "op_13",
      "numericId" : 13,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_24"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_26"
  }, {
    "track" : {
      "id" : "op_14",
      "numericId" : 14,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_26"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_27"
  }, {
    "track" : {
      "id" : "op_15",
      "numericId" : 15,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_27"
    }, {
      "key" : "b",
      "value" : "i_25"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_28"
  }, {
    "track" : {
      "id" : "op_16",
      "numericId" : 16,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_3"
    }, {
      "key" : "b",
      "value" : "i_29"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_31"
  }, {
    "track" : {
      "id" : "op_17",
      "numericId" : 17,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_31"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_32"
  }, {
    "track" : {
      "id" : "op_18",
      "numericId" : 18,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_32"
    }, {
      "key" : "b",
      "value" : "i_30"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_33"
  }, {
    "track" : {
      "id" : "op_19",
      "numericId" : 19,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
      "id" : "op_20",
      "numericId" : 20,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
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
      "id" : "op_21",
      "numericId" : 21,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_22",
      "numericId" : 22,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_39"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_41"
  }, {
    "track" : {
      "id" : "op_23",
      "numericId" : 23,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_41"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_42"
  }, {
    "track" : {
      "id" : "op_24",
      "numericId" : 24,
      "startedAt" : "2026-06-30T08:09:07.1599854Z",
      "finishedAt" : "2026-06-30T08:09:07.1599854Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_42"
    }, {
      "key" : "b",
      "value" : "i_40"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_43"
  }, {
    "track" : {
      "id" : "op_25",
      "numericId" : 25,
      "startedAt" : "2026-06-30T08:09:07.1612797Z",
      "finishedAt" : "2026-06-30T08:09:07.1612797Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_44"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_46"
  }, {
    "track" : {
      "id" : "op_26",
      "numericId" : 26,
      "startedAt" : "2026-06-30T08:09:07.1612797Z",
      "finishedAt" : "2026-06-30T08:09:07.1612797Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_46"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_47"
  }, {
    "track" : {
      "id" : "op_27",
      "numericId" : 27,
      "startedAt" : "2026-06-30T08:09:07.1612797Z",
      "finishedAt" : "2026-06-30T08:09:07.1612797Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_47"
    }, {
      "key" : "b",
      "value" : "i_45"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_48"
  }, {
    "track" : {
      "id" : "op_28",
      "numericId" : 28,
      "startedAt" : "2026-06-30T08:09:07.1612797Z",
      "finishedAt" : "2026-06-30T08:09:07.1612797Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_49"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_51"
  }, {
    "track" : {
      "id" : "op_29",
      "numericId" : 29,
      "startedAt" : "2026-06-30T08:09:07.1612797Z",
      "finishedAt" : "2026-06-30T08:09:07.1612797Z",
      "descriptor" : {
        "name" : "max",
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_51"
    }, {
      "key" : "b",
      "value" : "i_2"
    } ],
    "resultId" : "o_52"
  }, {
    "track" : {
      "id" : "op_30",
      "numericId" : 30,
      "startedAt" : "2026-06-30T08:09:07.1612797Z",
      "finishedAt" : "2026-06-30T08:09:07.1612797Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_52"
    }, {
      "key" : "b",
      "value" : "i_50"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_53"
  }, {
    "track" : {
      "id" : "op_31",
      "numericId" : 31,
      "startedAt" : "2026-06-30T08:09:07.1632811Z",
      "finishedAt" : "2026-06-30T08:09:07.1632811Z",
      "descriptor" : {
        "name" : "addBulk",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_8"
    }, {
      "key" : "b0",
      "value" : "o_13"
    }, {
      "key" : "b1",
      "value" : "o_18"
    }, {
      "key" : "b2",
      "value" : "o_23"
    }, {
      "key" : "b3",
      "value" : "o_28"
    }, {
      "key" : "b4",
      "value" : "o_33"
    }, {
      "key" : "b5",
      "value" : "o_38"
    }, {
      "key" : "b6",
      "value" : "o_43"
    }, {
      "key" : "b7",
      "value" : "o_48"
    }, {
      "key" : "b8",
      "value" : "o_53"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_54"
  } ]
}

</CPG>

---

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly. The user message will tell you which of these sets (if any) are relevant to the specific analysis you are asked to perform, and how to use them.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these, e.g. by using a near-duplicate rather than an exact-duplicate identifier or name. The entire reason this analysis is delegated to you rather than a script is to catch what exact-match heuristics cannot; absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_50, i_9, i_30, i_40, i_10, i_20, i_34, i_45, i_44, i_14, i_25, i_24, i_35, i_49, i_15, i_29, i_39, i_19, i_1, i_3, i_2, i_5, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_54]
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
