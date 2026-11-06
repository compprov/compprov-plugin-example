## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "E-commerce: order total"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Computation precision (DECIMAL64)"
},
"id" : "i_1",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 16,
"roundingMode" : "HALF_EVEN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Computation precision"
},
"id" : "i_2",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 2,
"roundingMode" : "DOWN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "1.0"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price [SKU-1001]"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "29.99"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity [SKU-1001]"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Line total [SKU-1001]"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "89.97"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price [SKU-2002]"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "14.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity [SKU-2002]"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Line total [SKU-2002]"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "29.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price [SKU-3003]"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "59.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity [SKU-3003]"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Line total [SKU-3003]"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "59.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price [SKU-4004]"
},
"id" : "i_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.99"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity [SKU-4004]"
},
"id" : "i_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Line total [SKU-4004]"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "49.95"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Subtotal"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "227.92"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Discount rate (SAVE10)"
},
"id" : "i_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Discount multiplier (SAVE10)"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.90"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Subtotal after SAVE10"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "205.1280"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Discount rate (LOYALTY5)"
},
"id" : "i_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.05"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Discount multiplier (LOYALTY5)"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.95"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Subtotal after LOYALTY5"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.9E+2"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "taxable",
"value" : "true"
}, {
"key" : "jurisdiction",
"value" : "California"
} ],
"name" : "Shipping fee"
},
"id" : "i_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Taxable amount"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "202.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tax rate (8%, California)"
},
"id" : "i_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.08"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sales tax"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "16.2000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Order total"
},
"id" : "o_27",
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
"id" : "op_1",
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
"id" : "op_2",
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
"id" : "op_3",
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
"id" : "op_4",
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
"id" : "op_5",
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
"id" : "op_6",
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
"id" : "op_7",
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
"id" : "op_8",
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
"id" : "op_9",
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
"id" : "op_10",
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
"id" : "op_11",
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
"id" : "op_12",
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

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_7, i_8, i_10, i_11, i_13, i_14, i_17, i_20, i_23, i_25]
- **Leaf variable IDs**: [o_27]
- **Variable IDs consumed as an argument by more than one operation**: [i_3, o_24]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
