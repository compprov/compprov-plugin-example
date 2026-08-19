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
    "name" : "Retirement: 6-year annuity projection"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1894469Z",
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
      "createdAt" : "2026-08-05T08:19:33.1894469Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Annual contribution"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1894469Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Annual growth rate (7%)"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.07"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1894469Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance (start)"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1894469Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest earned [Year 1]"
      },
      "id" : "o_5",
      "kind" : "OUTPUT",
      "numericId" : 5,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1894469Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after interest [Year 1]"
      },
      "id" : "o_6",
      "kind" : "OUTPUT",
      "numericId" : 6,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after contribution [Year 1]"
      },
      "id" : "o_7",
      "kind" : "OUTPUT",
      "numericId" : 7,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest earned [Year 2]"
      },
      "id" : "o_8",
      "kind" : "OUTPUT",
      "numericId" : 8,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "420.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after interest [Year 2]"
      },
      "id" : "o_9",
      "kind" : "OUTPUT",
      "numericId" : 9,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6420.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after contribution [Year 2]"
      },
      "id" : "o_10",
      "kind" : "OUTPUT",
      "numericId" : 10,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "12420.0000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest earned [Year 3]"
      },
      "id" : "o_11",
      "kind" : "OUTPUT",
      "numericId" : 11,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "869.400000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after interest [Year 3]"
      },
      "id" : "o_12",
      "kind" : "OUTPUT",
      "numericId" : 12,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "13289.400000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after contribution [Year 3]"
      },
      "id" : "o_13",
      "kind" : "OUTPUT",
      "numericId" : 13,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "19289.400000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest earned [Year 4]"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1350.25800000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after interest [Year 4]"
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20639.65800000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after contribution [Year 4]"
      },
      "id" : "o_16",
      "kind" : "OUTPUT",
      "numericId" : 16,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "26639.65800000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest earned [Year 5]"
      },
      "id" : "o_17",
      "kind" : "OUTPUT",
      "numericId" : 17,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1864.7760600000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after interest [Year 5]"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "28504.4340600000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after contribution [Year 5]"
      },
      "id" : "o_19",
      "kind" : "OUTPUT",
      "numericId" : 19,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "34504.4340600000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Interest earned [Year 6]"
      },
      "id" : "o_20",
      "kind" : "OUTPUT",
      "numericId" : 20,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2415.310384200000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Balance after interest [Year 6]"
      },
      "id" : "o_21",
      "kind" : "OUTPUT",
      "numericId" : 21,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "36919.74444420000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Ending balance"
      },
      "id" : "o_22",
      "kind" : "OUTPUT",
      "numericId" : 22,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "42919.74444420000"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Contribution years"
      },
      "id" : "i_23",
      "kind" : "INPUT",
      "numericId" : 23,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total contributions"
      },
      "id" : "o_24",
      "kind" : "OUTPUT",
      "numericId" : 24,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "36000.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1904551Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total growth (interest earned)"
      },
      "id" : "o_25",
      "kind" : "OUTPUT",
      "numericId" : 25,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "6919.74444420000"
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
      "finishedAt" : "2026-08-05T08:19:33.1894469Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-08-05T08:19:33.1894469Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_4"
    }, {
      "key" : "b",
      "value" : "i_3"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1894469Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-08-05T08:19:33.1894469Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_4"
    }, {
      "key" : "b",
      "value" : "o_5"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-08-05T08:19:33.1894469Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_6"
    }, {
      "key" : "b",
      "value" : "i_2"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_7"
    }, {
      "key" : "b",
      "value" : "i_3"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_7"
    }, {
      "key" : "b",
      "value" : "o_8"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_9"
    }, {
      "key" : "b",
      "value" : "i_2"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_10"
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
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_10"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_12"
    }, {
      "key" : "b",
      "value" : "i_2"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_13"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_13"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_15"
    }, {
      "key" : "b",
      "value" : "i_2"
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
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_13",
      "numericId" : 13,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
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
    "resultId" : "o_17"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_14",
      "numericId" : 14,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_16"
    }, {
      "key" : "b",
      "value" : "o_17"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_15",
      "numericId" : 15,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_18"
    }, {
      "key" : "b",
      "value" : "i_2"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_16",
      "numericId" : 16,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_19"
    }, {
      "key" : "b",
      "value" : "i_3"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_17",
      "numericId" : 17,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_19"
    }, {
      "key" : "b",
      "value" : "o_20"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_18",
      "numericId" : 18,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_21"
    }, {
      "key" : "b",
      "value" : "i_2"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_22"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_19",
      "numericId" : 19,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1904551Z",
      "id" : "op_20",
      "numericId" : 20,
      "startedAt" : "2026-08-05T08:19:33.1904551Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_22"
    }, {
      "key" : "b",
      "value" : "o_24"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_25"
  } ]
}

</CPG>

---

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly. The user message will tell you which of these sets (if any) are relevant to the specific analysis you are asked to perform, and how to use them.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these, e.g. by using a near-duplicate rather than an exact-duplicate identifier or name. The entire reason this analysis is delegated to you rather than a script is to catch what exact-match heuristics cannot; absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_23, i_1, i_3, i_2, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_25]
- **Variable IDs consumed as an argument by more than one operation** (`MathContext` variables, which are legitimately reused across operations, are excluded from this list): [i_2, i_3, i_4, o_7, o_10, o_13, o_16, o_19]
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
