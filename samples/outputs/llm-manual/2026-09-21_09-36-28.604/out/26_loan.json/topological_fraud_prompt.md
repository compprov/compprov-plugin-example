## ROLE
You are a Principal Computational Provenance Auditor and Security Engineer, examining Directed Acyclic Graphs (DAGs) that record computational execution traces across financial, scientific, and engineering pipelines. Evaluate whether the CPG violates the specific invariants defined below. Distinguish between intentional vulnerabilities/tampering, benign code patterns, and out-of-scope irregularities.

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
    "name" : "Loan: 6-month amortization"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3101127Z",
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
      "createdAt" : "2026-09-14T07:43:41.3116166Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Monthly payment"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2000.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3136236Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Monthly period rate (0.4%)"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.004"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3136236Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Extra principal prepayment (month 4)"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5000.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3136236Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Starting principal"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "240000.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3156246Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest accrued [Month 1]"
      },
      "id" : "o_6",
      "kind" : "OUTPUT",
      "numericId" : 6,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "960.00000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Principal portion [Month 1]"
      },
      "id" : "o_7",
      "kind" : "OUTPUT",
      "numericId" : 7,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1040.00000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after payment [Month 1]"
      },
      "id" : "o_8",
      "kind" : "OUTPUT",
      "numericId" : 8,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "238960.00000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Escrow collected [Month 1]"
      },
      "id" : "i_9",
      "kind" : "INPUT",
      "numericId" : 9,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "400.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest accrued [Month 2]"
      },
      "id" : "o_10",
      "kind" : "OUTPUT",
      "numericId" : 10,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "955.84000000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Principal portion [Month 2]"
      },
      "id" : "o_11",
      "kind" : "OUTPUT",
      "numericId" : 11,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1044.16000000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after payment [Month 2]"
      },
      "id" : "o_12",
      "kind" : "OUTPUT",
      "numericId" : 12,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "237915.84000000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Escrow collected [Month 2]"
      },
      "id" : "i_13",
      "kind" : "INPUT",
      "numericId" : 13,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "400.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest accrued [Month 3]"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "951.66336000000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Principal portion [Month 3]"
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1048.33664000000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after payment [Month 3]"
      },
      "id" : "o_16",
      "kind" : "OUTPUT",
      "numericId" : 16,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "236867.5033600000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Escrow collected [Month 3]"
      },
      "id" : "i_17",
      "kind" : "INPUT",
      "numericId" : 17,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "400.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest accrued [Month 4]"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "947.4700134400000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Principal portion [Month 4]"
      },
      "id" : "o_19",
      "kind" : "OUTPUT",
      "numericId" : 19,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1052.529986560000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after payment [Month 4]"
      },
      "id" : "o_20",
      "kind" : "OUTPUT",
      "numericId" : 20,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "235814.9733734400"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after prepayment [Month 4]"
      },
      "id" : "o_21",
      "kind" : "OUTPUT",
      "numericId" : 21,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "230814.9733734400"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Escrow collected [Month 4]"
      },
      "id" : "i_22",
      "kind" : "INPUT",
      "numericId" : 22,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "400.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest accrued [Month 5]"
      },
      "id" : "o_23",
      "kind" : "OUTPUT",
      "numericId" : 23,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "923.2598934937600"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Principal portion [Month 5]"
      },
      "id" : "o_24",
      "kind" : "OUTPUT",
      "numericId" : 24,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1076.740106506240"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after payment [Month 5]"
      },
      "id" : "o_25",
      "kind" : "OUTPUT",
      "numericId" : 25,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "229738.2332669338"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Escrow collected [Month 5]"
      },
      "id" : "i_26",
      "kind" : "INPUT",
      "numericId" : 26,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "400.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest accrued [Month 6]"
      },
      "id" : "o_27",
      "kind" : "OUTPUT",
      "numericId" : 27,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "918.9529330677352"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Principal portion [Month 6]"
      },
      "id" : "o_28",
      "kind" : "OUTPUT",
      "numericId" : 28,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1081.047066932265"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Ending balance"
      },
      "id" : "o_29",
      "kind" : "OUTPUT",
      "numericId" : 29,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "228657.1862000015"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Escrow collected [Month 6]"
      },
      "id" : "i_30",
      "kind" : "INPUT",
      "numericId" : 30,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "400.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total interest accrued"
      },
      "id" : "o_31",
      "kind" : "OUTPUT",
      "numericId" : 31,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5657.186200001495"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total escrow collected"
      },
      "id" : "o_32",
      "kind" : "OUTPUT",
      "numericId" : 32,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2400.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total escrow"
      },
      "id" : "i_33",
      "kind" : "INPUT",
      "numericId" : 33,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2280.0000"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total scheduled payments (6 months)"
      },
      "id" : "o_34",
      "kind" : "OUTPUT",
      "numericId" : 34,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "12000.00"
  }, {
    "track" : {
      "createdAt" : "2026-09-14T07:43:41.3171277Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total amount paid by borrower"
      },
      "id" : "o_35",
      "kind" : "OUTPUT",
      "numericId" : 35,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "19280.0000"
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
      "finishedAt" : "2026-09-14T07:43:41.3156246Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-09-14T07:43:41.3156246Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_5"
    }, {
      "key" : "b",
      "value" : "i_3"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b",
      "value" : "o_6"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
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
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_8"
    }, {
      "key" : "b",
      "value" : "i_3"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b",
      "value" : "o_10"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_8"
    }, {
      "key" : "b",
      "value" : "o_11"
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
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_12"
    }, {
      "key" : "b",
      "value" : "i_3"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b",
      "value" : "o_14"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_12"
    }, {
      "key" : "b",
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_16"
    }, {
      "key" : "b",
      "value" : "i_3"
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
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
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
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_16"
    }, {
      "key" : "b",
      "value" : "o_19"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_13",
      "numericId" : 13,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_20"
    }, {
      "key" : "b",
      "value" : "i_4"
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
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_14",
      "numericId" : 14,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_21"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_23"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_15",
      "numericId" : 15,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b",
      "value" : "o_23"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_16",
      "numericId" : 16,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_21"
    }, {
      "key" : "b",
      "value" : "o_24"
    }, {
      "key" : "mc",
      "value" : "i_1"
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
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_17",
      "numericId" : 17,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_25"
    }, {
      "key" : "b",
      "value" : "i_3"
    }, {
      "key" : "mc",
      "value" : "i_1"
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
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_18",
      "numericId" : 18,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b",
      "value" : "o_27"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_28"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_19",
      "numericId" : 19,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_25"
    }, {
      "key" : "b",
      "value" : "o_28"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_29"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_20",
      "numericId" : 20,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_6"
    }, {
      "key" : "b0",
      "value" : "o_10"
    }, {
      "key" : "b1",
      "value" : "o_14"
    }, {
      "key" : "b2",
      "value" : "o_18"
    }, {
      "key" : "b3",
      "value" : "o_23"
    }, {
      "key" : "b4",
      "value" : "o_27"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_31"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_21",
      "numericId" : 21,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_9"
    }, {
      "key" : "b0",
      "value" : "i_13"
    }, {
      "key" : "b1",
      "value" : "i_17"
    }, {
      "key" : "b2",
      "value" : "i_22"
    }, {
      "key" : "b3",
      "value" : "i_26"
    }, {
      "key" : "b4",
      "value" : "i_30"
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
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_22",
      "numericId" : 22,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b0",
      "value" : "i_2"
    }, {
      "key" : "b1",
      "value" : "i_2"
    }, {
      "key" : "b2",
      "value" : "i_2"
    }, {
      "key" : "b3",
      "value" : "i_2"
    }, {
      "key" : "b4",
      "value" : "i_2"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_34"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-09-14T07:43:41.3171277Z",
      "id" : "op_23",
      "numericId" : 23,
      "startedAt" : "2026-09-14T07:43:41.3171277Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_34"
    }, {
      "key" : "b0",
      "value" : "i_33"
    }, {
      "key" : "b1",
      "value" : "i_4"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_35"
  } ]
}

</CPG>

---

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these. Absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_9, i_30, i_22, i_33, i_13, i_1, i_26, i_3, i_2, i_17, i_5, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_35, o_29, o_32, o_31]
- **Variable IDs consumed as an argument by more than one operation** (`MathContext` variables, which are legitimately reused across operations, are excluded from this list): [i_2, i_3, i_4, i_5, o_6, o_8, o_10, o_12, o_14, o_16, o_18, o_21, o_23, o_25, o_27]
- **Leaf variables that share their exact display name (`descriptor.name`) with another variable elsewhere in the graph**: [] (none detected)

---

## OBJECTIVE
Analyze the computation graph above to detect potential **Topological & Provenance Fraud** along the graph's causal paths into its terminal result.

Focus on DAG structure, path multiplicity (M=0, M>1), and wrong source IDs.

---

## ATTACK VECTOR DEFINITION: Topological & Provenance Fraud

For every variable whose name, role, or metadata identifies it as a mandatory contributor to the pipeline's final reported result — a cost, deduction, credit, correction, cross-check, revenue entity, or any other component the result's own formula implies it should include, in any domain, not only financial — define its **path multiplicity** $M(V, Op_{terminal})$ as the number of distinct causal paths by which that variable's own correctly-computed value reaches the graph's true terminal output(s). A well-formed, untampered pipeline has $M = 1$ for every such contributor. The three attacks in this family are exactly the three ways that invariant can fail, and they are graded on the same evidence: trace all directed paths from each qualifying variable forward to the terminal output(s) — do not stop at the first aggregation encountered, since an intermediate subtotal can itself be re-consumed by a later aggregation or adjustment step, and reuse there is just as much a violation as reuse at the first step.

### Calculation Omission ($M = 0$)
The variable is computed or measured correctly and transparently, but never reaches the terminal aggregation at all — left as an unconsumed dead-end (leaf), or quietly dropped from the argument list of the final result operation despite that operation's own name, role, and metadata indicating it should be included. This biases the reported result toward a more favorable or simpler outcome than a complete computation would produce, and it is domain-agnostic: an excluded tax liability, a skipped correction factor in an engineering simulation, and an unused cross-validation measurement in a metrology pipeline are all the identical shape of fraud.

### Topological Accumulation Fraud via Double Counting ($M > 1$)
The variable's value — an inflow/revenue value **or** a cost/deduction value, the fraud is identical in shape either direction — is fed into the terminal aggregation via more than one causal path, artificially inflating or deflating the reported result. Common mechanisms: mapping a single source variable into both a primary path and a secondary, look-alike path that both feed the same rollup; routing one entity through multiple distinct intermediate operations before aggregating them; re-wrapping a variable through an identity/passthrough operation to assign it a new `track.id` while its underlying reference is unchanged; or, the deduction-side mirror, netting a cost out of one branch's intermediate subtotal and then subtracting that same cost again from a later aggregate that already incorporates that subtotal. Deduplicated sum $S_{dedup}$ (each unique root entity counted once) must equal the reported consolidation $S_{reported}$; it usually does not when this attack is present.

### Lineage Disconnection and Context Substitution ($M = 1$, wrong source)
Cardinality looks correct — exactly one path reaches the terminal result — but the path does not originate from the variable's own computation chain. A legitimate sequence of upstream operations executes and produces a correctly-computed variable, which is then left as an orphaned dead-end, while a foreign, hardcoded, or unmonitored value is substituted in its place at the critical downstream step, engineered so the substituted step still replays deterministically on its own. The test is not "does local replay pass" — it usually does. It is whether $Origin\_Propagation\_Valid$ holds: does the reported terminal output actually derive from the graph's own full computation chain, forward-propagated from true root inputs, or does it consume a substituted context somewhere along the way? Perform the full forward propagation and compare $O_{derived}$ against $O_{reported}$; do not rely on local, single-step replay.

---

## USING THE STRUCTURAL REFERENCE DATA
All four sets above are relevant to this task, each anchoring a different one of the three sub-patterns above:

- **Leaf variable IDs** ($M = 0$ candidates). Any leaf whose name, role, or metadata marks it as a mandatory adjustment, deduction, credit, correction, or cross-check is a Calculation Omission candidate — reconstruct the formula the terminal result *should* reflect from every such variable and confirm the terminal operation's actual arguments include it. A leaf is only a legitimate terminal result if nothing else in the graph was consumed downstream in its place for the same purpose (see the next bullet).
- **Leaf variables that share their exact display name with another variable elsewhere in the graph** ($M = 1$-wrong-source candidates). This is close to a direct signal for Lineage Disconnection: for each entry, inspect the identically-named variable(s) it collides with. If the leaf is a computed `OUTPUT` and the colliding variable is an `INPUT` with no producing operation that *is* consumed downstream — reaching a reported terminal output while the properly computed one does not — that is the textbook signature of context substitution, not a naming coincidence. This set only catches exact string matches; treat it as a floor. A substitution can hide behind a semantically similar name (reworded, abbreviated, translated) or a different name entirely that plays the identical role (same units, same position in the formula, overlapping `descriptor.meta`, or a value that looks like a rounded/truncated twin). Scan every leaf for a plausible stand-in by role, not only by name.
- **Variable IDs consumed as an argument by more than one operation** ($M > 1$ candidates). Use these to focus the path-multiplicity analysis for Double Counting. Reuse alone is not a verdict — `MathContext` and other genuine global reference parameters are legitimately reused and are already excluded from this list — but for every remaining ID, determine whether the reuse is a documented, auditable proportional split, or genuine duplication into the same terminal aggregation. Also check for duplication this list cannot catch: the same underlying entity re-entered under a different variable ID (origin/hash aliasing) rather than literal ID reuse.
- **Root variable IDs**. Cross-check these against both the omission and substitution patterns above: an unused root can indicate a legitimate external boundary parameter that was superseded by a substitution elsewhere, and a root `INPUT` with no producing operation that exactly mirrors a computed sibling's name, units, or role is itself a live Lineage Disconnection candidate, independent of the leaf-collision set.

**Rationalizations that will feel persuasive here and are all wrong:**
- *"The substitute is declared as a root INPUT with full metadata, so it's transparent."* Being declared as an `INPUT` is not a defense — it is the mechanism. A hardcoded constant masquerading under a computed variable's exact name, sitting openly with `kind: INPUT`, is exactly what a substituted value looks like from inside a CPG.
- *"The two values are numerically very close, so this is reasonable precision truncation."* A close numeric match is exactly what a well-disguised substitution looks like — a wildly different stand-in would be caught by casual inspection. The question is never how far apart the numbers are, it's whether the consumed value derives from the same computation chain as its orphaned twin.
- *"The computed twin is never itself an argument to the operation that consumes its substitute — independent, non-competing branches."* This inverts cause and effect: when Lineage Disconnection is present, the properly-computed variable will *always* show zero downstream consumers, because that is what "bypassed" means. The branches not intersecting is the necessary structural signature of the attack, not evidence against it.
- *"This reused variable is just a shared allocation basis, and reuse is common and benign."* True in general — but the question is never whether reuse *can* be benign, it's whether *this specific* reuse converges back into the *same* terminal aggregation node more than once. A shared tax rate consumed by five independent, non-aggregating branches is not the same shape as a single asset value consumed twice on paths that both terminate in the same rollup.
- *"The omitted variable was probably left as a diagnostic or audit-trail output, not a dropped deduction."* A genuine standalone diagnostic output has no formula-level claim on the terminal result's name, role, or metadata. If the variable's own descriptor identifies it as the kind of quantity the terminal result's formula should incorporate, "it's just for reference" is the exact rationalization to reject rather than accept at face value.

<EXPECTED_INVARIANTS>
- For every variable identified as a mandatory contributor to the pipeline's final reported result, path multiplicity $M(V, Op_{terminal})$ into the terminal output must equal exactly 1. $M = 0$ (an unconsumed dead-end while the pipeline reports as though the computation were complete) is a Calculation Omission. $M > 1$ (the same origin entity reaches the terminal result via more than one causal path, whether contributing as an addend or as a subtracted deduction, without explicit documented split/allocation logic) is Double Counting.
- $Origin\_Propagation\_Valid$ must hold for the terminal output: it must derive from full forward propagation from the graph's own true root inputs through its own computation chain, not merely pass local replay at each node it touches. Downstream operations must consume the exact output variable generated by the preceding logical step — test literally whether the consumed argument is the actual `resultId` of that step, or a stand-in for it. A violation here, at $M = 1$, is Lineage Disconnection and Context Substitution.
- Re-wrapping or cloning an intermediate variable through an identity/passthrough operation does not grant it unique entity status, and does not reset its path multiplicity, if its lineage traces back to a previously consumed root entity or a previously computed sibling.
- Hardcoded literals or static baseline overrides are strictly forbidden at any junction where a computed sibling for the same quantity exists elsewhere in the graph. This does not apply to genuine constants (mathematical constants, unit-conversion factors, precision contexts) with no computed sibling anywhere in the graph.
- Deduplicated sum $S_{dedup}$ (each unique root entity counted once) must match the reported consolidation $S_{reported}$.
</EXPECTED_INVARIANTS>

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine invariant violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and note the remaining ambiguity about intent for the reader to resolve. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical omission, duplication, or substitution is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if you already extracted a variable's role, identity, or lineage as one thing, you cannot silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use `risk_score` (0–100) to measure the probability and severity of invariant violations, structural tampering, or arithmetic flaws in the graph:
- 0 represents a completely clean graph with zero evidence of tampering or invariant violations.
- 100 represents a confirmed, material attack vector or severe structural exploit.

Use intermediate score ranges to accurately calibrate uncertainty or ambiguous evidence:
- If a finding is clearly real and material, reflect that with a high risk score.
- If you find a genuine irregularity but cannot determine whether it stems from intentional tampering, expected variance, or an unstated domain convention, assign an intermediate risk score — do not erase the ambiguity by defaulting to a low risk score.

---

## REQUIRED OUTPUT FORMAT

Return your audit report as markdown, using this structure exactly:

### Risk Score
[Output an integer from 0–100 calculated according to the AUDIT DISCIPLINE guidelines.]

### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
