## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Depreciation: 5-year straight-line schedule"
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
"name" : "Asset cost"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "85000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Salvage value"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Useful life (years)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Depreciable base"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "75000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Annual depreciation"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "15000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Accumulated depreciation (start)"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Accumulated depreciation [Year 1]"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "15000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Book value [Year 1]"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "70000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Accumulated depreciation [Year 2]"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "30000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Book value [Year 2]"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "55000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Accumulated depreciation [Year 3]"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "45000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Book value [Year 3]"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "40000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Accumulated depreciation [Year 4]"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "60000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Book value [Year 4]"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "25000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Accumulated depreciation [Year 5]"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "75000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Book value [Year 5]"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10000.00"
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
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_5"
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
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "o_6"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_4",
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "b",
"value" : "o_6"
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
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "o_6"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_6"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
}, {
"key" : "b",
"value" : "o_6"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "o_6"
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
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "o_6"
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
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "o_6"
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
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_15"
}, {
"key" : "b",
"value" : "o_6"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_17"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_7]
- **Leaf variable IDs**: [o_16, o_17]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, o_6]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
