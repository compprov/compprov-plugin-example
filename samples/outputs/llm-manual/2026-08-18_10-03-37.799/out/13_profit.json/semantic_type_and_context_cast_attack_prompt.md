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
    "name" : "DeFi portfolio profit calculation"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
      "descriptor" : {
        "meta" : [ {
          "key" : "source",
          "value" : "AAVE, fully collateralized 1:1"
        } ],
        "name" : "WBTC\/BTC peg rate"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "WBTC",
      "to" : "BTC",
      "rate" : "1"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4480"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4420"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_7",
      "kind" : "INPUT",
      "numericId" : 7,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4390"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_8",
      "kind" : "INPUT",
      "numericId" : 8,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4310"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_9",
      "kind" : "INPUT",
      "numericId" : 9,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.00369452",
      "currency" : "WBTC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_10",
      "kind" : "INPUT",
      "numericId" : 10,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.027353420000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_11",
      "kind" : "INPUT",
      "numericId" : 11,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "119.583561",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_12",
      "kind" : "INPUT",
      "numericId" : 12,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "299.589041",
      "currency" : "USDT"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_13",
      "kind" : "INPUT",
      "numericId" : 13,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.169105630000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_14",
      "kind" : "INPUT",
      "numericId" : 14,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.005000000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_15",
      "kind" : "INPUT",
      "numericId" : 15,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.003960000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_16",
      "kind" : "INPUT",
      "numericId" : 16,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.003000000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_17",
      "kind" : "INPUT",
      "numericId" : 17,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.003960000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_18",
      "kind" : "INPUT",
      "numericId" : 18,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.004000000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
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
      "id" : "i_19",
      "kind" : "INPUT",
      "numericId" : 19,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.005200000000000000",
      "currency" : "ETH"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Platform fee rate (3%)"
      },
      "id" : "i_20",
      "kind" : "INPUT",
      "numericId" : 20,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.03"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (wBTC\/AAVE) in USDC"
      },
      "id" : "o_21",
      "kind" : "OUTPUT",
      "numericId" : 21,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "22.400000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (ETH\/AAVE) in USDC"
      },
      "id" : "o_22",
      "kind" : "OUTPUT",
      "numericId" : 22,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "17.384400",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1176975Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (USDC\/AAVE) in USDC"
      },
      "id" : "o_23",
      "kind" : "OUTPUT",
      "numericId" : 23,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "12.930000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (USDT\/Morpho) in USDC"
      },
      "id" : "o_24",
      "kind" : "OUTPUT",
      "numericId" : 24,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "17.503200",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (ETH\/Lido) in USDC"
      },
      "id" : "o_25",
      "kind" : "OUTPUT",
      "numericId" : 25,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "17.920000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gas (stETH\/EtherFi) in USDC"
      },
      "id" : "o_26",
      "kind" : "OUTPUT",
      "numericId" : 26,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "23.296000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total gas fees in USDC"
      },
      "id" : "o_27",
      "kind" : "OUTPUT",
      "numericId" : 27,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "111.433600",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "wBTC yield in BTC (peg-adjusted)"
      },
      "id" : "o_28",
      "kind" : "OUTPUT",
      "numericId" : 28,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.00369452",
      "currency" : "BTC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "wBTC yield in USDC"
      },
      "id" : "o_29",
      "kind" : "OUTPUT",
      "numericId" : 29,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "405.658296",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_30",
      "kind" : "OUTPUT",
      "numericId" : 30,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "383.258296",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH(AAVE) yield in USDC"
      },
      "id" : "o_31",
      "kind" : "OUTPUT",
      "numericId" : 31,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "127.193403",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_32",
      "kind" : "OUTPUT",
      "numericId" : 32,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "109.809003",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_33",
      "kind" : "OUTPUT",
      "numericId" : 33,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "106.653561",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "USDT(Morpho) yield in USDC"
      },
      "id" : "o_34",
      "kind" : "OUTPUT",
      "numericId" : 34,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "299.529123",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_35",
      "kind" : "OUTPUT",
      "numericId" : 35,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "282.025923",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH(Lido+EtherFi) yield in USDC"
      },
      "id" : "o_36",
      "kind" : "OUTPUT",
      "numericId" : 36,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "786.341179",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_37",
      "kind" : "OUTPUT",
      "numericId" : 37,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "768.421179",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : ""
      },
      "id" : "o_38",
      "kind" : "OUTPUT",
      "numericId" : 38,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "745.125179",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Gross yield in USDC"
      },
      "id" : "o_39",
      "kind" : "OUTPUT",
      "numericId" : 39,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "1626.871962",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Platform fee in USDC"
      },
      "id" : "o_40",
      "kind" : "OUTPUT",
      "numericId" : 40,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "48.806158",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "After gas deduction"
      },
      "id" : "o_41",
      "kind" : "OUTPUT",
      "numericId" : 41,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "1515.438362",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:33.1186995Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Net profit in USDC"
      },
      "id" : "o_42",
      "kind" : "OUTPUT",
      "numericId" : 42,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "1466.632204",
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
      "finishedAt" : "2026-08-05T08:19:33.1176975Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-08-05T08:19:33.1176975Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_14"
    }, {
      "key" : "r",
      "value" : "i_5"
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
      "finishedAt" : "2026-08-05T08:19:33.1176975Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-08-05T08:19:33.1176975Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_15"
    }, {
      "key" : "r",
      "value" : "i_7"
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
      "finishedAt" : "2026-08-05T08:19:33.1176975Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-08-05T08:19:33.1176975Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_16"
    }, {
      "key" : "r",
      "value" : "i_8"
    } ],
    "resultId" : "o_23"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-08-05T08:19:33.1176975Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_17"
    }, {
      "key" : "r",
      "value" : "i_6"
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
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_18"
    }, {
      "key" : "r",
      "value" : "i_5"
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
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_19"
    }, {
      "key" : "r",
      "value" : "i_5"
    } ],
    "resultId" : "o_26"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a+b0+...+bn"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_21"
    }, {
      "key" : "b0",
      "value" : "o_22"
    }, {
      "key" : "b1",
      "value" : "o_23"
    }, {
      "key" : "b2",
      "value" : "o_24"
    }, {
      "key" : "b3",
      "value" : "o_25"
    }, {
      "key" : "b4",
      "value" : "o_26"
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
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_9"
    }, {
      "key" : "r",
      "value" : "i_4"
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
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_28"
    }, {
      "key" : "r",
      "value" : "i_1"
    } ],
    "resultId" : "o_29"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_29"
    }, {
      "key" : "b",
      "value" : "o_21"
    } ],
    "resultId" : "o_30"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_10"
    }, {
      "key" : "r",
      "value" : "i_2"
    } ],
    "resultId" : "o_31"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_31"
    }, {
      "key" : "b",
      "value" : "o_22"
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
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_13",
      "numericId" : 13,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_11"
    }, {
      "key" : "b",
      "value" : "o_23"
    } ],
    "resultId" : "o_33"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_14",
      "numericId" : 14,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_12"
    }, {
      "key" : "r",
      "value" : "i_3"
    } ],
    "resultId" : "o_34"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_15",
      "numericId" : 15,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_34"
    }, {
      "key" : "b",
      "value" : "o_24"
    } ],
    "resultId" : "o_35"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "convert(a,r)"
        } ],
        "name" : "convert"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_16",
      "numericId" : 16,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_13"
    }, {
      "key" : "r",
      "value" : "i_2"
    } ],
    "resultId" : "o_36"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_17",
      "numericId" : 17,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_36"
    }, {
      "key" : "b",
      "value" : "o_25"
    } ],
    "resultId" : "o_37"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_18",
      "numericId" : 18,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_37"
    }, {
      "key" : "b",
      "value" : "o_26"
    } ],
    "resultId" : "o_38"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a+b0+...+bn"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_19",
      "numericId" : 19,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_30"
    }, {
      "key" : "b0",
      "value" : "o_32"
    }, {
      "key" : "b1",
      "value" : "o_33"
    }, {
      "key" : "b2",
      "value" : "o_35"
    }, {
      "key" : "b3",
      "value" : "o_38"
    } ],
    "resultId" : "o_39"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a*f"
        } ],
        "name" : "scale"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_20",
      "numericId" : 20,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_39"
    }, {
      "key" : "f",
      "value" : "i_20"
    } ],
    "resultId" : "o_40"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_21",
      "numericId" : 21,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_39"
    }, {
      "key" : "b",
      "value" : "o_27"
    } ],
    "resultId" : "o_41"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "a-b"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:33.1186995Z",
      "id" : "op_22",
      "numericId" : 22,
      "startedAt" : "2026-08-05T08:19:33.1186995Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_41"
    }, {
      "key" : "b",
      "value" : "o_40"
    } ],
    "resultId" : "o_42"
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
