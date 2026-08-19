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
    "meta" : [ ],
    "name" : "Insurance: claims adjudication"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
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
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Zero (claim floor)"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Collision"
        } ],
        "name" : "Claim amount"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "8000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Collision"
        } ],
        "name" : "Deductible"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "500.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Collision"
        } ],
        "name" : "Coinsurance rate"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.80"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Collision"
        } ],
        "name" : "Policy limit"
      },
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "10000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Collision: net of deductible"
      },
      "id" : "o_7",
      "kind" : "OUTPUT",
      "numericId" : 7,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "7500.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Collision: net of deductible (floored)"
      },
      "id" : "o_8",
      "kind" : "OUTPUT",
      "numericId" : 8,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "7500.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Collision: coinsurance amount"
      },
      "id" : "o_9",
      "kind" : "OUTPUT",
      "numericId" : 9,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6000.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Collision claim payout"
      },
      "id" : "o_10",
      "kind" : "OUTPUT",
      "numericId" : 10,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6000.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Comprehensive"
        } ],
        "name" : "Claim amount"
      },
      "id" : "i_11",
      "kind" : "INPUT",
      "numericId" : 11,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Comprehensive"
        } ],
        "name" : "Deductible"
      },
      "id" : "i_12",
      "kind" : "INPUT",
      "numericId" : 12,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Comprehensive"
        } ],
        "name" : "Coinsurance rate"
      },
      "id" : "i_13",
      "kind" : "INPUT",
      "numericId" : 13,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.90"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Comprehensive"
        } ],
        "name" : "Policy limit"
      },
      "id" : "i_14",
      "kind" : "INPUT",
      "numericId" : 14,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "12000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Comprehensive: net of deductible"
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "19000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Comprehensive: net of deductible (floored)"
      },
      "id" : "o_16",
      "kind" : "OUTPUT",
      "numericId" : 16,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "19000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Comprehensive: coinsurance amount"
      },
      "id" : "o_17",
      "kind" : "OUTPUT",
      "numericId" : 17,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "17100.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Comprehensive claim payout"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "12000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Liability"
        } ],
        "name" : "Claim amount"
      },
      "id" : "i_19",
      "kind" : "INPUT",
      "numericId" : 19,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Liability"
        } ],
        "name" : "Deductible"
      },
      "id" : "i_20",
      "kind" : "INPUT",
      "numericId" : 20,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "250.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Liability"
        } ],
        "name" : "Coinsurance rate"
      },
      "id" : "i_21",
      "kind" : "INPUT",
      "numericId" : 21,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "claimType",
          "value" : "Liability"
        } ],
        "name" : "Policy limit"
      },
      "id" : "i_22",
      "kind" : "INPUT",
      "numericId" : 22,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Liability: net of deductible"
      },
      "id" : "o_23",
      "kind" : "OUTPUT",
      "numericId" : 23,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4750.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Liability: net of deductible (floored)"
      },
      "id" : "o_24",
      "kind" : "OUTPUT",
      "numericId" : 24,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4750.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Liability: coinsurance amount"
      },
      "id" : "o_25",
      "kind" : "OUTPUT",
      "numericId" : 25,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4750.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Liability claim payout"
      },
      "id" : "o_26",
      "kind" : "OUTPUT",
      "numericId" : 26,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4750.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total payout (computed, unused)"
      },
      "id" : "o_27",
      "kind" : "OUTPUT",
      "numericId" : 27,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "22750.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total payout"
      },
      "id" : "i_28",
      "kind" : "INPUT",
      "numericId" : 28,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "22750.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.9845981Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Reinsurance recovery rate (40%)"
      },
      "id" : "i_29",
      "kind" : "INPUT",
      "numericId" : 29,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.40"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.985601Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Reinsurance recovery"
      },
      "id" : "o_30",
      "kind" : "OUTPUT",
      "numericId" : 30,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "9100.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.985601Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Net loss"
      },
      "id" : "o_31",
      "kind" : "OUTPUT",
      "numericId" : 31,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "13650.0000"
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
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
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
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
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
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_8"
    }, {
      "key" : "b",
      "value" : "i_5"
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
          "value" : "min(a,b)"
        } ],
        "name" : "min"
      },
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_9"
    }, {
      "key" : "b",
      "value" : "i_6"
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
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_11"
    }, {
      "key" : "b",
      "value" : "i_12"
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
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_15"
    }, {
      "key" : "b",
      "value" : "i_2"
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
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_16"
    }, {
      "key" : "b",
      "value" : "i_13"
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
          "value" : "min(a,b)"
        } ],
        "name" : "min"
      },
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_17"
    }, {
      "key" : "b",
      "value" : "i_14"
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
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_19"
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
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "max(a,b)"
        } ],
        "name" : "max"
      },
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_23"
    }, {
      "key" : "b",
      "value" : "i_2"
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
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_24"
    }, {
      "key" : "b",
      "value" : "i_21"
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
          "value" : "min(a,b)"
        } ],
        "name" : "min"
      },
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_25"
    }, {
      "key" : "b",
      "value" : "i_22"
    } ],
    "resultId" : "o_26"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:32.9845981Z",
      "id" : "op_13",
      "numericId" : 13,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_10"
    }, {
      "key" : "b0",
      "value" : "o_18"
    }, {
      "key" : "b1",
      "value" : "o_26"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.985601Z",
      "id" : "op_14",
      "numericId" : 14,
      "startedAt" : "2026-08-05T08:19:32.9845981Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_28"
    }, {
      "key" : "b",
      "value" : "i_29"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:32.985601Z",
      "id" : "op_15",
      "numericId" : 15,
      "startedAt" : "2026-08-05T08:19:32.985601Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_28"
    }, {
      "key" : "b",
      "value" : "o_30"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_31"
  } ]
}

</CPG>

---

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly. The user message will tell you which of these sets (if any) are relevant to the specific analysis you are asked to perform, and how to use them.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these, e.g. by using a near-duplicate rather than an exact-duplicate identifier or name. The entire reason this analysis is delegated to you rather than a script is to catch what exact-match heuristics cannot; absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_6, i_21, i_20, i_12, i_11, i_22, i_14, i_13, i_29, i_28, i_19, i_1, i_3, i_2, i_5, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_27, o_31]
- **Variable IDs consumed as an argument by more than one operation** (`MathContext` variables, which are legitimately reused across operations, are excluded from this list): [i_2, i_28]
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
