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
"name" : "1.0"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price [SKU-1001]"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "29.99"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity [SKU-1001]"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Line total [SKU-1001]"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "89.97"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price [SKU-2002]"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "14.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity [SKU-2002]"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Line total [SKU-2002]"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "29.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price [SKU-3003]"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "59.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity [SKU-3003]"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Line total [SKU-3003]"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "59.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price [SKU-4004]"
},
"id" : "i_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.99"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity [SKU-4004]"
},
"id" : "i_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Line total [SKU-4004]"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "49.95"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Subtotal"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "227.92"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Discount rate (SAVE10)"
},
"id" : "i_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Discount multiplier (SAVE10)"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.90"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Subtotal after SAVE10"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "205.1280"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Discount rate (LOYALTY5)"
},
"id" : "i_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.05"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Discount multiplier (LOYALTY5)"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.95"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Subtotal after LOYALTY5"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "194.871600"
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
"id" : "i_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Taxable amount"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "207.371600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tax rate (8%, California)"
},
"id" : "i_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.08"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sales tax"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "16.58972800"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Restocking fee (revenue)"
},
"id" : "i_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "15.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tax on restocking fee"
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.2000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Restocking fee total"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "16.2000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Order total"
},
"id" : "o_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "223.96132800"
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
"id" : "op_2",
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
"id" : "op_3",
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
"id" : "op_4",
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
"id" : "op_5",
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
"id" : "op_6",
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
"id" : "op_7",
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
"id" : "op_8",
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
"id" : "op_9",
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
"id" : "op_10",
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
"id" : "op_11",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_26"
}, {
"key" : "b",
"value" : "i_24"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_26"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_14",
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
"resultId" : "o_29"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_6, i_7, i_9, i_10, i_12, i_13, i_16, i_19, i_22, i_24, i_26]
- **Leaf variable IDs**: [o_28, o_29]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, o_23, i_24, i_26]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
