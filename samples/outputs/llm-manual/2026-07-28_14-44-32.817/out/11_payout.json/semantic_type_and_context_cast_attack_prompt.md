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
    "name" : "ETH\/USDC daily options payout"
  },
  "variables" : [ {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6109223Z",
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
      "createdAt" : "2026-07-27T09:30:16.6134359Z",
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
      "createdAt" : "2026-07-27T09:30:16.6154369Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "ETH\/USDC spot (2026-06-30)"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "io.compprov.examples.nav.model.Rate"
    },
    "value" : {
      "from" : "ETH",
      "to" : "USDC",
      "rate" : "4650"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6194675Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [0]"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "CALL",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4630"
      },
      "size" : {
        "amount" : "6.863900000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [1]"
      },
      "id" : "i_5",
      "kind" : "INPUT",
      "numericId" : 5,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "PUT",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4710"
      },
      "size" : {
        "amount" : "2.843100000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [2]"
      },
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "CALL",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4670"
      },
      "size" : {
        "amount" : "9.043400000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [3]"
      },
      "id" : "i_7",
      "kind" : "INPUT",
      "numericId" : 7,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "PUT",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4550"
      },
      "size" : {
        "amount" : "2.829900000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [4]"
      },
      "id" : "i_8",
      "kind" : "INPUT",
      "numericId" : 8,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "PUT",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4730"
      },
      "size" : {
        "amount" : "7.850700000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [5]"
      },
      "id" : "i_9",
      "kind" : "INPUT",
      "numericId" : 9,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "CALL",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4630"
      },
      "size" : {
        "amount" : "4.421300000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [6]"
      },
      "id" : "i_10",
      "kind" : "INPUT",
      "numericId" : 10,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "CALL",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4710"
      },
      "size" : {
        "amount" : "3.927000000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [7]"
      },
      "id" : "i_11",
      "kind" : "INPUT",
      "numericId" : 11,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "PUT",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4690"
      },
      "size" : {
        "amount" : "5.984100000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [8]"
      },
      "id" : "i_12",
      "kind" : "INPUT",
      "numericId" : 12,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "PUT",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4590"
      },
      "size" : {
        "amount" : "8.277100000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Position [9]"
      },
      "id" : "i_13",
      "kind" : "INPUT",
      "numericId" : 13,
      "valueClass" : "io.compprov.examples.nav.model.OptionPosition"
    },
    "value" : {
      "type" : "PUT",
      "strike" : {
        "from" : "ETH",
        "to" : "USDC",
        "rate" : "4550"
      },
      "size" : {
        "amount" : "5.915500000000000000",
        "currency" : "ETH"
      }
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.620467Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [0]"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "137.278000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6224672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [1]"
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "170.586000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6224672Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [2]"
      },
      "id" : "o_16",
      "kind" : "OUTPUT",
      "numericId" : 16,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.000000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6234682Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [3]"
      },
      "id" : "o_17",
      "kind" : "OUTPUT",
      "numericId" : 17,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.000000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6234682Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [4]"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "628.056000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6234682Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [5]"
      },
      "id" : "o_19",
      "kind" : "OUTPUT",
      "numericId" : 19,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "88.426000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6234682Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [6]"
      },
      "id" : "o_20",
      "kind" : "OUTPUT",
      "numericId" : 20,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.000000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6234682Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [7]"
      },
      "id" : "o_21",
      "kind" : "OUTPUT",
      "numericId" : 21,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "239.364000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6234682Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [8]"
      },
      "id" : "o_22",
      "kind" : "OUTPUT",
      "numericId" : 22,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.000000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6234682Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Payout [9]"
      },
      "id" : "o_23",
      "kind" : "OUTPUT",
      "numericId" : 23,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "0.000000",
      "currency" : "USDC"
    }
  }, {
    "track" : {
      "createdAt" : "2026-07-27T09:30:16.6234682Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Total payout in USDC"
      },
      "id" : "o_24",
      "kind" : "OUTPUT",
      "numericId" : 24,
      "valueClass" : "io.compprov.examples.nav.model.Amount"
    },
    "value" : {
      "amount" : "1263.710000",
      "currency" : "USDC"
    }
  } ],
  "operations" : [ {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.620467Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-07-27T09:30:16.620467Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_4"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_14"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6224672Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-07-27T09:30:16.6224672Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_5"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_15"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6224672Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-07-27T09:30:16.6224672Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_6"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_16"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6234682Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-07-27T09:30:16.6234682Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_7"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_17"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6234682Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-07-27T09:30:16.6234682Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_8"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_18"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6234682Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-07-27T09:30:16.6234682Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_9"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_19"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6234682Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-07-27T09:30:16.6234682Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_10"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_20"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6234682Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-07-27T09:30:16.6234682Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_11"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_21"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6234682Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-07-27T09:30:16.6234682Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_12"
    }, {
      "key" : "price",
      "value" : "i_3"
    } ],
    "resultId" : "o_22"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "pos.payout(price)"
        } ],
        "name" : "payout"
      },
      "finishedAt" : "2026-07-27T09:30:16.6234682Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-07-27T09:30:16.6234682Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "pos",
      "value" : "i_13"
    }, {
      "key" : "price",
      "value" : "i_3"
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
      "finishedAt" : "2026-07-27T09:30:16.6234682Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-07-27T09:30:16.6234682Z",
      "wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_14"
    }, {
      "key" : "b0",
      "value" : "o_15"
    }, {
      "key" : "b1",
      "value" : "o_16"
    }, {
      "key" : "b2",
      "value" : "o_17"
    }, {
      "key" : "b3",
      "value" : "o_18"
    }, {
      "key" : "b4",
      "value" : "o_19"
    }, {
      "key" : "b5",
      "value" : "o_20"
    }, {
      "key" : "b6",
      "value" : "o_21"
    }, {
      "key" : "b7",
      "value" : "o_22"
    }, {
      "key" : "b8",
      "value" : "o_23"
    } ],
    "resultId" : "o_24"
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
