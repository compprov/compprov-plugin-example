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
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
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
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "1.0"
      },
      "id" : "i_2",
      "kind" : "INPUT",
      "numericId" : 2,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Unit price [SKU-1001]"
      },
      "id" : "i_3",
      "kind" : "INPUT",
      "numericId" : 3,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "29.99"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Quantity [SKU-1001]"
      },
      "id" : "i_4",
      "kind" : "INPUT",
      "numericId" : 4,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "3"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Line total [SKU-1001]"
      },
      "id" : "o_5",
      "kind" : "OUTPUT",
      "numericId" : 5,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "89.97"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Unit price [SKU-2002]"
      },
      "id" : "i_6",
      "kind" : "INPUT",
      "numericId" : 6,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "14.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Quantity [SKU-2002]"
      },
      "id" : "i_7",
      "kind" : "INPUT",
      "numericId" : 7,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Line total [SKU-2002]"
      },
      "id" : "o_8",
      "kind" : "OUTPUT",
      "numericId" : 8,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "29.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Unit price [SKU-3003]"
      },
      "id" : "i_9",
      "kind" : "INPUT",
      "numericId" : 9,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "59.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Quantity [SKU-3003]"
      },
      "id" : "i_10",
      "kind" : "INPUT",
      "numericId" : 10,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Line total [SKU-3003]"
      },
      "id" : "o_11",
      "kind" : "OUTPUT",
      "numericId" : 11,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "59.00"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Unit price [SKU-4004]"
      },
      "id" : "i_12",
      "kind" : "INPUT",
      "numericId" : 12,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "9.99"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Quantity [SKU-4004]"
      },
      "id" : "i_13",
      "kind" : "INPUT",
      "numericId" : 13,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Line total [SKU-4004]"
      },
      "id" : "o_14",
      "kind" : "OUTPUT",
      "numericId" : 14,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "49.95"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Subtotal"
      },
      "id" : "o_15",
      "kind" : "OUTPUT",
      "numericId" : 15,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "227.92"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Discount rate (SAVE10)"
      },
      "id" : "i_16",
      "kind" : "INPUT",
      "numericId" : 16,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.10"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Discount multiplier (SAVE10)"
      },
      "id" : "o_17",
      "kind" : "OUTPUT",
      "numericId" : 17,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.90"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Subtotal after SAVE10"
      },
      "id" : "o_18",
      "kind" : "OUTPUT",
      "numericId" : 18,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "205.1280"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Discount rate (LOYALTY5)"
      },
      "id" : "i_19",
      "kind" : "INPUT",
      "numericId" : 19,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.05"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Discount multiplier (LOYALTY5)"
      },
      "id" : "o_20",
      "kind" : "OUTPUT",
      "numericId" : 20,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.95"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Subtotal after LOYALTY5"
      },
      "id" : "o_21",
      "kind" : "OUTPUT",
      "numericId" : 21,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "194.871600"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Shipping fee"
      },
      "id" : "i_22",
      "kind" : "INPUT",
      "numericId" : 22,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "12.50"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Taxable amount"
      },
      "id" : "o_23",
      "kind" : "OUTPUT",
      "numericId" : 23,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "207.371600"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Tax rate (8%, California)"
      },
      "id" : "i_24",
      "kind" : "INPUT",
      "numericId" : 24,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.02"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Sales tax"
      },
      "id" : "o_25",
      "kind" : "OUTPUT",
      "numericId" : 25,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4.14743200"
  }, {
    "track" : {
      "createdAt" : "2026-08-05T08:19:32.8687671Z",
      "descriptor" : {
        "meta" : [ ],
        "name" : "Order total"
      },
      "id" : "o_26",
      "kind" : "OUTPUT",
      "numericId" : 26,
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "211.51903200"
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
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
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
    "resultId" : "o_5"
  }, {
    "track" : {
      "descriptor" : {
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_6"
    }, {
      "key" : "b",
      "value" : "i_7"
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
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_9"
    }, {
      "key" : "b",
      "value" : "i_10"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_12"
    }, {
      "key" : "b",
      "value" : "i_13"
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
          "value" : "(a+b0+...+bn)mc"
        } ],
        "name" : "addBulk"
      },
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_5"
    }, {
      "key" : "b0",
      "value" : "o_8"
    }, {
      "key" : "b1",
      "value" : "o_11"
    }, {
      "key" : "b2",
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
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b",
      "value" : "i_16"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_15"
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
          "value" : "(a-b)mc"
        } ],
        "name" : "subtract"
      },
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "i_2"
    }, {
      "key" : "b",
      "value" : "i_19"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_18"
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
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_21"
    }, {
      "key" : "b",
      "value" : "i_22"
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
          "value" : "(a*b)mc"
        } ],
        "name" : "multiply"
      },
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_23"
    }, {
      "key" : "b",
      "value" : "i_24"
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
          "value" : "(a+b)mc"
        } ],
        "name" : "add"
      },
      "finishedAt" : "2026-08-05T08:19:32.8687671Z",
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-08-05T08:19:32.8687671Z",
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_23"
    }, {
      "key" : "b",
      "value" : "o_25"
    }, {
      "key" : "mc",
      "value" : "i_1"
    } ],
    "resultId" : "o_26"
  } ]
}

</CPG>

---

## STRUCTURAL REFERENCE DATA
The following sets were computed by deterministic graph traversal over the CPG above (not by the audit you are about to perform). They are structural facts, not a verdict — they are not necessarily exhaustive, and their presence alone does not indicate an anomaly.

These sets are produced by cheap, mechanical checks (exact ID/name matching) — they are a starting point for your analysis, not a substitute for it. A sophisticated adversary would design their tampering specifically to slip past a naive automated check like these, e.g. by using a near-duplicate rather than an exact-duplicate identifier or name. Absence from one of these sets is not evidence of absence of the underlying issue.

- **Root variable IDs** (`INPUT` variables with no producing operation): [i_7, i_6, i_9, i_10, i_12, i_22, i_13, i_24, i_16, i_19, i_1, i_3, i_2, i_4]
- **Leaf variable IDs** (variables never consumed as an argument by any operation): [o_26]
- **Variable IDs consumed as an argument by more than one operation** (`MathContext` variables, which are legitimately reused across operations, are excluded from this list): [i_2, o_23]
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
