## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Heat conduction: two-layer wall, steady state"
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
"name" : "Interior temperature (°C)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "22.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Exterior temperature (°C)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Wall area, A (m²)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Layer 1 (brick) thermal conductivity, k1 (W/(m·K))"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.72"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Layer 1 (brick) thickness, d1 (m)"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Layer 2 (insulation) thermal conductivity, k2 (W/(m·K))"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.04"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Layer 2 (insulation) thickness, d2 (m)"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.05"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total temperature difference, interior - exterior (K)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "17.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Thermal resistance, layer 1 (m²·K/W)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.1388888888888889"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Thermal resistance, layer 2 (m²·K/W)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.25"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total thermal resistance (m²·K/W)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.388888888888889"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Steady-state heat flux (W/m²)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.24000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Temperature drop across layer 1 (K)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.700000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interface temperature, brick/insulation (°C)"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20.30000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Temperature drop across layer 2 (K)"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "15.30000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "k1 × A (layer 1)"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7.200"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "k1 × A × ΔT1 (layer 1)"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.24000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Heat flow through layer 1, Q1 (W)"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "122.400000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "k2 × A (layer 2)"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.400"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "k2 × A × ΔT2 (layer 2)"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.120000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Heat flow through layer 2, Q2 (W)"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "122.4000000000000"
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
"resultId" : "o_9"
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
"value" : "i_6"
}, {
"key" : "b",
"value" : "i_5"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
}, {
"key" : "b",
"value" : "i_7"
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
"id" : "op_4",
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "o_12"
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
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "o_10"
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
"id" : "op_7",
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
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_15"
}, {
"key" : "b",
"value" : "i_3"
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
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
}, {
"key" : "b",
"value" : "i_4"
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
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "o_14"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
}, {
"key" : "b",
"value" : "i_6"
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
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "i_4"
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
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
}, {
"key" : "b",
"value" : "o_16"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "b",
"value" : "i_8"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_22"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8]
- **Leaf variable IDs**: [o_19, o_22]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, i_4, i_5, i_6, i_7, i_8, o_10, o_14]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
