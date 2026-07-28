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
    "name" : "DeFi portfolio profit calculation"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.5994065Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "date",
          "value" : "2026-06-30"
        }, {
          "key" : "source",
          "value" : "market"
        } ],
        "name" : "BTC\/USDC rate"
      },
      "id" : "i_1",
      "kind" : "INPUT",
      "numericId" : 1,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "BTC",
      "to" : "USDC",
      "rate" : "109800"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.601921Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "date",
          "value" : "2026-06-30"
        }, {
          "key" : "source",
          "value" : "market"
        } ],
        "name" : "ETH\/USDC rate"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4650"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.601921Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "date",
          "value" : "2026-06-30"
        }, {
          "key" : "source",
          "value" : "market"
        } ],
        "name" : "USDT\/USDC rate"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "USDT",
      "to" : "USDC",
      "rate" : "0.9998"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.601921Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "date",
          "value" : "2026-06-01"
        }, {
          "key" : "source",
          "value" : "market"
        } ],
        "name" : "ETH\/USDC rate"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4480"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.601921Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "date",
          "value" : "2026-06-03"
        }, {
          "key" : "source",
          "value" : "market"
        } ],
        "name" : "ETH\/USDC rate"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4420"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.601921Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "date",
          "value" : "2026-06-04"
        }, {
          "key" : "source",
          "value" : "market"
        } ],
        "name" : "ETH\/USDC rate"
      },
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4390"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.601921Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "date",
          "value" : "2026-06-08"
        }, {
          "key" : "source",
          "value" : "market"
        } ],
        "name" : "ETH\/USDC rate"
      },
      "id" : "i_7",
      "kind" : "INPUT",
      "numericId" : 7,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4310"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.601921Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "protocol",
          "value" : "AAVE"
        }, {
          "key" : "network",
          "value" : "ETH"
        }, {
          "key" : "deposit",
          "value" : "2026-06-01"
        } ],
        "name" : "wBTC yield"
      },
      "id" : "i_8",
      "kind" : "INPUT",
      "numericId" : 8,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.00369452",
      "currency" : "BTC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6029202Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "protocol",
          "value" : "AAVE"
        }, {
          "key" : "network",
          "value" : "ETH"
        }, {
          "key" : "deposit",
          "value" : "2026-06-04"
        } ],
        "name" : "ETH yield"
      },
      "id" : "i_9",
      "kind" : "INPUT",
      "numericId" : 9,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.027353420000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6029202Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "protocol",
          "value" : "AAVE"
        }, {
          "key" : "network",
          "value" : "ETH"
        }, {
          "key" : "deposit",
          "value" : "2026-06-08"
        } ],
        "name" : "USDC yield"
      },
      "id" : "i_10",
      "kind" : "INPUT",
      "numericId" : 10,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "119.583561",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6029202Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "protocol",
          "value" : "Morpho"
        }, {
          "key" : "deposit",
          "value" : "2026-06-03"
        } ],
        "name" : "USDT yield"
      },
      "id" : "i_11",
      "kind" : "INPUT",
      "numericId" : 11,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "299.589041",
      "currency" : "USDT"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6029202Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "protocol",
          "value" : "Lido+EtherFi"
        }, {
          "key" : "network",
          "value" : "ETH"
        }, {
          "key" : "deposit",
          "value" : "2026-06-01"
        } ],
        "name" : "ETH yield"
      },
      "id" : "i_12",
      "kind" : "INPUT",
      "numericId" : 12,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.169105630000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6029202Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "tx",
          "value" : "wBTC→AAVE"
        }, {
          "key" : "date",
          "value" : "2026-06-01"
        } ],
        "name" : "Gas (ETH)"
      },
      "id" : "i_13",
      "kind" : "INPUT",
      "numericId" : 13,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.005000000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6039197Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "tx",
          "value" : "ETH→AAVE"
        }, {
          "key" : "date",
          "value" : "2026-06-04"
        } ],
        "name" : "Gas (ETH)"
      },
      "id" : "i_14",
      "kind" : "INPUT",
      "numericId" : 14,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.003960000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6039197Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "tx",
          "value" : "USDC→AAVE"
        }, {
          "key" : "date",
          "value" : "2026-06-08"
        } ],
        "name" : "Gas (ETH)"
      },
      "id" : "i_15",
      "kind" : "INPUT",
      "numericId" : 15,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.003000000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6039197Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "tx",
          "value" : "USDT→Morpho"
        }, {
          "key" : "date",
          "value" : "2026-06-03"
        } ],
        "name" : "Gas (ETH)"
      },
      "id" : "i_16",
      "kind" : "INPUT",
      "numericId" : 16,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.003960000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6039197Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "tx",
          "value" : "ETH→Lido"
        }, {
          "key" : "date",
          "value" : "2026-06-01"
        } ],
        "name" : "Gas (ETH)"
      },
      "id" : "i_17",
      "kind" : "INPUT",
      "numericId" : 17,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.004000000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6039197Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "tx",
          "value" : "stETH→EtherFi"
        }, {
          "key" : "date",
          "value" : "2026-06-01"
        } ],
        "name" : "Gas (ETH)"
      },
      "id" : "i_18",
      "kind" : "INPUT",
      "numericId" : 18,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.005200000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6039197Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Platform fee rate (3%)"
      },
      "id" : "i_19",
      "kind" : "INPUT",
      "numericId" : 19,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.03"
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6059188Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "wBTC yield in USDC"
      },
      "id" : "o_20",
      "kind" : "OUTPUT",
      "numericId" : 20,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "405.658296",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6069186Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH(AAVE) yield in USDC"
      },
      "id" : "o_21",
      "kind" : "OUTPUT",
      "numericId" : 21,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "127.193403",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6069186Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "USDT(Morpho) yield in USDC"
      },
      "id" : "o_22",
      "kind" : "OUTPUT",
      "numericId" : 22,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "299.529123",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6069186Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH(Lido+EtherFi) yield in USDC"
      },
      "id" : "o_23",
      "kind" : "OUTPUT",
      "numericId" : 23,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "786.341179",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gross yield in USDC"
      },
      "id" : "o_24",
      "kind" : "OUTPUT",
      "numericId" : 24,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "1738.305562",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (wBTC\/AAVE) in USDC"
      },
      "id" : "o_25",
      "kind" : "OUTPUT",
      "numericId" : 25,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "22.400000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (ETH\/AAVE) in USDC"
      },
      "id" : "o_26",
      "kind" : "OUTPUT",
      "numericId" : 26,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "17.384400",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (USDC\/AAVE) in USDC"
      },
      "id" : "o_27",
      "kind" : "OUTPUT",
      "numericId" : 27,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "12.930000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (USDT\/Morpho) in USDC"
      },
      "id" : "o_28",
      "kind" : "OUTPUT",
      "numericId" : 28,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "17.503200",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (ETH\/Lido) in USDC"
      },
      "id" : "o_29",
      "kind" : "OUTPUT",
      "numericId" : 29,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "17.920000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (stETH\/EtherFi) in USDC"
      },
      "id" : "o_30",
      "kind" : "OUTPUT",
      "numericId" : 30,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "23.296000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total gas fees in USDC"
      },
      "id" : "o_31",
      "kind" : "OUTPUT",
      "numericId" : 31,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "111.433600",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Platform fee in USDC"
      },
      "id" : "o_32",
      "kind" : "OUTPUT",
      "numericId" : 32,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "52.149166",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "After gas deduction"
      },
      "id" : "o_33",
      "kind" : "OUTPUT",
      "numericId" : 33,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "1626.871962",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:32:22.6079192Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Net profit in USDC"
      },
      "id" : "o_34",
      "kind" : "OUTPUT",
      "numericId" : 34,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "1574.722796",
      "currency" : "USDC"
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
      "finishedAt" : "2026-07-27T09:32:22.6059188Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-07-27T09:32:22.6059188Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_8"
    }, {
      "key" : "r",
      "value" : "i_1"
    } ],
    "resultId" : "o_20"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6069186Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-07-27T09:32:22.6069186Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_9"
    }, {
      "key" : "r",
      "value" : "i_2"
    } ],
    "resultId" : "o_21"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6069186Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-07-27T09:32:22.6069186Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_11"
    }, {
      "key" : "r",
      "value" : "i_3"
    } ],
    "resultId" : "o_22"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6069186Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-07-27T09:32:22.6069186Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_12"
    }, {
      "key" : "r",
      "value" : "i_2"
    } ],
    "resultId" : "o_23"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a+b0+...+bn"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_20"
    }, {
      "key" : "b0",
      "value" : "o_21"
    }, {
      "key" : "b1",
      "value" : "i_10"
    }, {
      "key" : "b2",
      "value" : "o_22"
    }, {
      "key" : "b3",
      "value" : "o_23"
    } ],
    "resultId" : "o_24"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_13"
    }, {
      "key" : "r",
      "value" : "i_4"
    } ],
    "resultId" : "o_25"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_14"
    }, {
      "key" : "r",
      "value" : "i_6"
    } ],
    "resultId" : "o_26"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_15"
    }, {
      "key" : "r",
      "value" : "i_7"
    } ],
    "resultId" : "o_27"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_16"
    }, {
      "key" : "r",
      "value" : "i_5"
    } ],
    "resultId" : "o_28"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_17"
    }, {
      "key" : "r",
      "value" : "i_4"
    } ],
    "resultId" : "o_29"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_18"
    }, {
      "key" : "r",
      "value" : "i_4"
    } ],
    "resultId" : "o_30"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a+b0+...+bn"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_25"
    }, {
      "key" : "b0",
      "value" : "o_26"
    }, {
      "key" : "b1",
      "value" : "o_27"
    }, {
      "key" : "b2",
      "value" : "o_28"
    }, {
      "key" : "b3",
      "value" : "o_29"
    }, {
      "key" : "b4",
      "value" : "o_30"
    } ],
    "resultId" : "o_31"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a*f"
        } ],
        "name" : "scale"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_13",
      "numericId" : 13,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_24"
    }, {
      "key" : "f",
      "value" : "i_19"
    } ],
    "resultId" : "o_32"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_14",
      "numericId" : 14,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_24"
    }, {
      "key" : "b",
      "value" : "o_31"
    } ],
    "resultId" : "o_33"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-07-27T09:32:22.6079192Z",
      "id" : "op_15",
      "numericId" : 15,
      "startedAt" : "2026-07-27T09:32:22.6079192Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_33"
    }, {
      "key" : "b",
      "value" : "o_32"
    } ],
    "resultId" : "o_34"
  } ]
}

</CPG>

---

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these, e.g. by using a near-duplicate rather than an exact-duplicate identifier or name. Absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_7, i_6, i_9, i_8, i_10, i_12, i_11, i_14, i_13, i_16, i_15, i_18, i_17, i_19, i_1, i_3, i_2, i_5, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_34]
- **Variable IDs consumed as an argument by more than one operation** (`MathContext` variables, which are legitimately reused across operations, are excluded from this list): [i_2, i_4, o_24]
- **Leaf variables that share their exact display name (`descriptor.name`) with another variable elsewhere in the graph**: [] (none detected)

---

## OBJECTIVE
Analyze the computation graph above to detect potential Lineage Disconnection and Context Substitution attacks, where local mathematical replay passes cleanly, but the causal data lineage between original root inputs and final compliance outputs has been covertly severed.

---

## ATTACK VECTOR DEFINITION: Lineage Disconnection and Context Substitution
Lineage Disconnection and Context Substitution occurs when an insider adversary or compromised pipeline maintains local deterministic mathematical validity while stealthily breaking end-to-end data provenance — executing a legitimate sequence of upstream operations to generate a verified variable, then substituting it at a critical downstream step with a foreign, hardcoded, or unmonitored value; leaving the correctly-computed variable as an orphaned dead-end while a parallel, injected value is quietly routed into the final calculation instead; ensuring the substituted step still replays deterministically so a local re-execution converges cleanly, masking that its inputs don't actually originate from the prior step; or hiding the rupture inside a massive, high-density graph where standard traversal tools only check local node syntax.

The test is not "does local replay pass" — it usually does. It is whether $Origin\_Propagation\_Valid$ holds: does the reported final output actually derive from the graph's own full computation chain, forward-propagated from true root inputs, or does it consume a substituted context somewhere along the way? Do not rely solely on local deterministic replay — perform the full forward propagation and compare $O_{derived}$ against $O_{reported}$.

---

## USING THE STRUCTURAL REFERENCE DATA
Of the sets above, the **root variable IDs**, **leaf variable IDs**, and **leaf variables sharing a name with another variable** are all relevant to this task.

The name-collision set is close to a direct signal for this attack when it fires. For each entry, check the identically-named variable(s) the leaf collides with: if the leaf is a computed `OUTPUT` and the variable it shares a name with is an `INPUT` (no producing operation) that *is* consumed downstream — reaching a reported final output while the properly computed one does not — that is the textbook signature of Intermediate Context Hijacking, not a coincidence of naming.

**Three rationalizations will feel persuasive here and are all wrong:**
- *"The hardcoded variable is declared as a root INPUT with full metadata, so it's transparent."* Being declared as an `INPUT` is not a defense — it is the mechanism. A hardcoded constant masquerading under a computed variable's exact name, sitting openly with `kind: INPUT`, is exactly what a "foreign, hardcoded, or unmonitored external value" looks like from inside a CPG. "Declared" describes where the substitute sits in the file, not where its value came from.
- *"The two values are numerically very close, so this is reasonable precision truncation."* A close numeric match is exactly what a well-disguised substitution looks like — a wildly different stand-in would be caught by casual inspection. The question is never "how far apart are the numbers," it's "does the consumed value derive from the same computation chain as its orphaned twin, or from an independent source that merely approximates it."
- *"The computed twin is never itself an argument to the operation that consumes the hardcoded one — independent, non-competing branches, not a substitution."* This inverts cause and effect: when this attack is present, the properly-computed variable will *always* show zero downstream consumers, because that's what "bypassed" means. The branches not intersecting is the necessary structural signature of the attack, not evidence against it.

The name-collision set above only catches exact string matches — treat it as a floor, not a ceiling. A substitution can hide behind a merely semantically similar name (reworded, abbreviated, translated) or a completely different name that plays the identical role (same units, same position in the formula, same domain metadata). For every leaf — not only ones flagged above — scan for a plausible stand-in by role, not just by name: overlapping `descriptor.meta`, or a value that looks like a rounded/truncated version of the leaf's own value. If found consumed downstream while the properly computed leaf is not, treat it as the same signature even though it didn't appear in the exact-match list.

<EXPECTED_INVARIANTS>
- Every final OUTPUT node must maintain full structural and semantic reachability back to root INPUT nodes *through the graph's own computation chain for that quantity* — reaching some root input is not sufficient if a computed sibling for the exact same quantity exists elsewhere in the graph and was bypassed in favor of it.
- A computed `OUTPUT` variable that goes unconsumed is only a legitimate terminal result if nothing else in the graph was used downstream in its place for the same purpose — if a different, non-computed variable serving the same role was consumed instead, labeling the unconsumed one "terminal" to exempt it from scrutiny is the exact rationalization to reject.
- Downstream operations must consume the exact output variable generated by the preceding logical step, not a hardcoded constant or unverified cached state — test this literally: was the consumed argument the actual `resultId` of the preceding computation, or a stand-in? If the latter, this is violated, full stop.
- Hardcoded literals or static baseline overrides are strictly forbidden at critical calculation junctures. Being "explicitly declared as a constant root input" does **not** exempt a variable from this — a hardcoded substitute is, by definition, always representable as a declared root `INPUT` in this graph format (there is no other way to inject a literal), so this is precisely the disguise to catch, not a legitimate carve-out. This does not apply to genuine constants (mathematical constants, unit-conversion factors, precision contexts) that have no computed sibling for the same quantity anywhere in the graph.
</EXPECTED_INVARIANTS>

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine invariant violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and note the remaining ambiguity about intent for the reader to resolve. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical substitution is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if you already extracted a variable's type, unit, or meaning as one thing, your verdict can't silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use your confidence score to carry calibration, rather than resolving it by force-fitting the verdict. If a finding is clearly real and material, say so with a high score. If you found something genuinely irregular but aren't sure it rises to tampering rather than expected variance or a legitimate design you can't fully rule out, report that assessment and reflect the doubt in a lower confidence score — don't make the uncertainty disappear by defaulting the verdict to CLEAN instead.

---

## REQUIRED OUTPUT FORMAT

Return your audit report as markdown, using this structure exactly:

### Verdict
One of: `CLEAN` | `LINEAGE BREAK DETECTED` | `SUSPICIOUS SUBSTITUTION`

### Confidence Score
A number from 0-100.

### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
