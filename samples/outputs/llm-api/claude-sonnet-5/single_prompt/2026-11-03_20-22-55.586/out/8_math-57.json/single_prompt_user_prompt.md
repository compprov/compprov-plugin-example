## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Clustering: k-means++ seeding of the second centre"
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
"name" : "First centre"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Distance scale"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant 1"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial sum, 0"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Point"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Offset from nearest centre"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Unscaled distance"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Distance to nearest centre"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Squared distance"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Sum before narrowing"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Cumulative squared distance"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Point"
},
"id" : "i_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Offset from nearest centre"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Unscaled distance"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Distance to nearest centre"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Squared distance"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Sum before narrowing"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Cumulative squared distance"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Point"
},
"id" : "i_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Offset from nearest centre"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Unscaled distance"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Distance to nearest centre"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Squared distance"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Sum before narrowing"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Cumulative squared distance"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Point"
},
"id" : "i_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Offset from nearest centre"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Unscaled distance"
},
"id" : "o_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Distance to nearest centre"
},
"id" : "o_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Squared distance"
},
"id" : "o_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Sum before narrowing"
},
"id" : "o_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Cumulative squared distance"
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Point"
},
"id" : "i_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Offset from nearest centre"
},
"id" : "o_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Unscaled distance"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Distance to nearest centre"
},
"id" : "o_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Squared distance"
},
"id" : "o_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Sum before narrowing"
},
"id" : "o_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Cumulative squared distance"
},
"id" : "o_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Point"
},
"id" : "i_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Offset from nearest centre"
},
"id" : "o_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Unscaled distance"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Distance to nearest centre"
},
"id" : "o_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Squared distance"
},
"id" : "o_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Sum before narrowing"
},
"id" : "o_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Cumulative squared distance"
},
"id" : "o_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Point"
},
"id" : "i_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Offset from nearest centre"
},
"id" : "o_49",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Unscaled distance"
},
"id" : "o_50",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Distance to nearest centre"
},
"id" : "o_51",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Squared distance"
},
"id" : "o_52",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Sum before narrowing"
},
"id" : "o_53",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Cumulative squared distance"
},
"id" : "o_54",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Point"
},
"id" : "i_55",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Offset from nearest centre"
},
"id" : "o_56",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Unscaled distance"
},
"id" : "o_57",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Distance to nearest centre"
},
"id" : "o_58",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Squared distance"
},
"id" : "o_59",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Sum before narrowing"
},
"id" : "o_60",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Cumulative squared distance"
},
"id" : "o_61",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Point"
},
"id" : "i_62",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Offset from nearest centre"
},
"id" : "o_63",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Unscaled distance"
},
"id" : "o_64",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Distance to nearest centre"
},
"id" : "o_65",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Squared distance"
},
"id" : "o_66",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Sum before narrowing"
},
"id" : "o_67",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Cumulative squared distance"
},
"id" : "o_68",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Uniform random draw, u"
},
"id" : "i_69",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.73"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Selection threshold, r"
},
"id" : "o_70",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Zero"
},
"id" : "i_71",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "One"
},
"id" : "i_72",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Cumulative squared distance vs threshold"
},
"id" : "o_73",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Below threshold, −1 = yes"
},
"id" : "o_74",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Threshold reached, 1 = yes"
},
"id" : "o_75",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Chosen, 1 = yes"
},
"id" : "o_76",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Point index"
},
"id" : "i_77",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Chosen · index"
},
"id" : "o_78",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] No point chosen yet, 1 = yes"
},
"id" : "o_79",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Cumulative squared distance vs threshold"
},
"id" : "o_80",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Below threshold, −1 = yes"
},
"id" : "o_81",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Threshold reached, 1 = yes"
},
"id" : "o_82",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Chosen, 1 = yes"
},
"id" : "o_83",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Point index"
},
"id" : "i_84",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Chosen · index"
},
"id" : "o_85",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] No point chosen yet, 1 = yes"
},
"id" : "o_86",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Cumulative squared distance vs threshold"
},
"id" : "o_87",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Below threshold, −1 = yes"
},
"id" : "o_88",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Threshold reached, 1 = yes"
},
"id" : "o_89",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Chosen, 1 = yes"
},
"id" : "o_90",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Point index"
},
"id" : "i_91",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Chosen · index"
},
"id" : "o_92",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] No point chosen yet, 1 = yes"
},
"id" : "o_93",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Cumulative squared distance vs threshold"
},
"id" : "o_94",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Below threshold, −1 = yes"
},
"id" : "o_95",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Threshold reached, 1 = yes"
},
"id" : "o_96",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Chosen, 1 = yes"
},
"id" : "o_97",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Point index"
},
"id" : "i_98",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Chosen · index"
},
"id" : "o_99",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] No point chosen yet, 1 = yes"
},
"id" : "o_100",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Cumulative squared distance vs threshold"
},
"id" : "o_101",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Below threshold, −1 = yes"
},
"id" : "o_102",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Threshold reached, 1 = yes"
},
"id" : "o_103",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Chosen, 1 = yes"
},
"id" : "o_104",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Point index"
},
"id" : "i_105",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Chosen · index"
},
"id" : "o_106",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] No point chosen yet, 1 = yes"
},
"id" : "o_107",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Cumulative squared distance vs threshold"
},
"id" : "o_108",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Below threshold, −1 = yes"
},
"id" : "o_109",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Threshold reached, 1 = yes"
},
"id" : "o_110",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Chosen, 1 = yes"
},
"id" : "o_111",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Point index"
},
"id" : "i_112",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Chosen · index"
},
"id" : "o_113",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] No point chosen yet, 1 = yes"
},
"id" : "o_114",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Cumulative squared distance vs threshold"
},
"id" : "o_115",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Below threshold, −1 = yes"
},
"id" : "o_116",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Threshold reached, 1 = yes"
},
"id" : "o_117",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Chosen, 1 = yes"
},
"id" : "o_118",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Point index"
},
"id" : "i_119",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Chosen · index"
},
"id" : "o_120",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] No point chosen yet, 1 = yes"
},
"id" : "o_121",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Cumulative squared distance vs threshold"
},
"id" : "o_122",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Below threshold, −1 = yes"
},
"id" : "o_123",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Threshold reached, 1 = yes"
},
"id" : "o_124",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Chosen, 1 = yes"
},
"id" : "o_125",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Point index"
},
"id" : "i_126",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Chosen · index"
},
"id" : "o_127",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] No point chosen yet, 1 = yes"
},
"id" : "o_128",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Cumulative squared distance vs threshold"
},
"id" : "o_129",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Below threshold, −1 = yes"
},
"id" : "o_130",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Threshold reached, 1 = yes"
},
"id" : "o_131",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Chosen, 1 = yes"
},
"id" : "o_132",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Point index"
},
"id" : "i_133",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Chosen · index"
},
"id" : "o_134",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Index of the second centre"
},
"id" : "o_135",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
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
"value" : "i_6"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
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
"value" : "o_8"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "o_9"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
}, {
"key" : "b",
"value" : "o_10"
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
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
}, {
"key" : "b",
"value" : "i_4"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_13"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_9",
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
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "o_16"
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
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
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
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
}, {
"key" : "b",
"value" : "i_4"
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
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_20"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
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
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "i_3"
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
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_23"
}, {
"key" : "b",
"value" : "o_23"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_19"
}, {
"key" : "b",
"value" : "o_24"
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
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_25"
}, {
"key" : "b",
"value" : "i_4"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_27"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_28"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_29"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_29"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_30"
}, {
"key" : "b",
"value" : "o_30"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_31"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "o_31"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_32"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_24",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_32"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_33"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_25",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_34"
}, {
"key" : "b",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_35"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_35"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_36"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_27",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_36"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_37"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_28",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_37"
}, {
"key" : "b",
"value" : "o_37"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_38"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_29",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_33"
}, {
"key" : "b",
"value" : "o_38"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_39"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_30",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_39"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_40"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_31",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_41"
}, {
"key" : "b",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_42"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_32",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_42"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_43"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_33",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_43"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_44"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_34",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_44"
}, {
"key" : "b",
"value" : "o_44"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_45"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_35",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_40"
}, {
"key" : "b",
"value" : "o_45"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_46"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_36",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_46"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_47"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_37",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_48"
}, {
"key" : "b",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_49"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_38",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_49"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_50"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_39",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_50"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_51"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_40",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_51"
}, {
"key" : "b",
"value" : "o_51"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_52"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_41",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_47"
}, {
"key" : "b",
"value" : "o_52"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_53"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_42",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_53"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_54"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_43",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_55"
}, {
"key" : "b",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_56"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_44",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_56"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_57"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_45",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_57"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_58"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_46",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_58"
}, {
"key" : "b",
"value" : "o_58"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_59"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_47",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_54"
}, {
"key" : "b",
"value" : "o_59"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_60"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_48",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_60"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_61"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_49",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_63"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_50",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_63"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_64"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_51",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_64"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_65"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_52",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_65"
}, {
"key" : "b",
"value" : "o_65"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_66"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_53",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_61"
}, {
"key" : "b",
"value" : "o_66"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_67"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_54",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_67"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_68"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_55",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_69"
}, {
"key" : "b",
"value" : "o_68"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_70"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_56",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_73"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_57",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_73"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_74"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_58",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_74"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_75"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_59",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_75"
}, {
"key" : "b",
"value" : "i_72"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_76"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_60",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_76"
}, {
"key" : "b",
"value" : "i_77"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_78"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_61",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_76"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_79"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_62",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_19"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_80"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_63",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_80"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_81"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_64",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_81"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_82"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_65",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_82"
}, {
"key" : "b",
"value" : "o_79"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_83"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_66",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_83"
}, {
"key" : "b",
"value" : "i_84"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_85"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_67",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_79"
}, {
"key" : "b",
"value" : "o_83"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_86"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_68",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_87"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_69",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_87"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_88"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_70",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_88"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_89"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_71",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_89"
}, {
"key" : "b",
"value" : "o_86"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_90"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_72",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_90"
}, {
"key" : "b",
"value" : "i_91"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_92"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_73",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_86"
}, {
"key" : "b",
"value" : "o_90"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_93"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_74",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_33"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_94"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_75",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_94"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_95"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_76",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_95"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_96"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_77",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_96"
}, {
"key" : "b",
"value" : "o_93"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_97"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_78",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_97"
}, {
"key" : "b",
"value" : "i_98"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_99"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_79",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_93"
}, {
"key" : "b",
"value" : "o_97"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_100"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_80",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_40"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_101"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_81",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_101"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_102"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_82",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_102"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_103"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_83",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_103"
}, {
"key" : "b",
"value" : "o_100"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_104"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_84",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_104"
}, {
"key" : "b",
"value" : "i_105"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_106"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_85",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_100"
}, {
"key" : "b",
"value" : "o_104"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_107"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_86",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_47"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_108"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_87",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_108"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_109"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_88",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_109"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_110"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_89",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_110"
}, {
"key" : "b",
"value" : "o_107"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_111"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_90",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_111"
}, {
"key" : "b",
"value" : "i_112"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_113"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_91",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_107"
}, {
"key" : "b",
"value" : "o_111"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_114"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_92",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_54"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_115"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_93",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_115"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_116"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_94",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_116"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_117"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_95",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_117"
}, {
"key" : "b",
"value" : "o_114"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_118"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_96",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_118"
}, {
"key" : "b",
"value" : "i_119"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_120"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_97",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_114"
}, {
"key" : "b",
"value" : "o_118"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_121"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_98",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_61"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_122"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_99",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_122"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_123"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_100",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_123"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_124"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_101",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_124"
}, {
"key" : "b",
"value" : "o_121"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_125"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_102",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_125"
}, {
"key" : "b",
"value" : "i_126"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_127"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_103",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_121"
}, {
"key" : "b",
"value" : "o_125"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_128"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_104",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_68"
}, {
"key" : "b",
"value" : "o_70"
} ],
"resultId" : "o_129"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_105",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_129"
}, {
"key" : "b",
"value" : "i_71"
} ],
"resultId" : "o_130"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_106",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_130"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_131"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_107",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_131"
}, {
"key" : "b",
"value" : "o_128"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_132"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_108",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_132"
}, {
"key" : "b",
"value" : "i_133"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_134"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_109",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_78"
}, {
"key" : "b0",
"value" : "o_85"
}, {
"key" : "b1",
"value" : "o_92"
}, {
"key" : "b2",
"value" : "o_99"
}, {
"key" : "b3",
"value" : "o_106"
}, {
"key" : "b4",
"value" : "o_113"
}, {
"key" : "b5",
"value" : "o_120"
}, {
"key" : "b6",
"value" : "o_127"
}, {
"key" : "b7",
"value" : "o_134"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_135"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_13, i_20, i_27, i_34, i_41, i_48, i_55, i_62, i_69, i_71, i_72, i_77, i_84, i_91, i_98, i_105, i_112, i_119, i_126, i_133]
- **Leaf variable IDs**: [o_135]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, i_4, o_12, o_19, o_26, o_33, o_40, o_47, o_54, o_61, o_68, o_70, i_71, i_72, o_76, o_79, o_83, o_86, o_90, o_93, o_97, o_100, o_104, o_107, o_111, o_114, o_118, o_121, o_125]
- **Operations that consume the same variable more than once**: op_4 ("multiply") consumes [o_9] more than once; op_10 ("multiply") consumes [o_16] more than once; op_16 ("multiply") consumes [o_23] more than once; op_22 ("multiply") consumes [o_30] more than once; op_28 ("multiply") consumes [o_37] more than once; op_34 ("multiply") consumes [o_44] more than once; op_40 ("multiply") consumes [o_51] more than once; op_46 ("multiply") consumes [o_58] more than once; op_52 ("multiply") consumes [o_65] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
