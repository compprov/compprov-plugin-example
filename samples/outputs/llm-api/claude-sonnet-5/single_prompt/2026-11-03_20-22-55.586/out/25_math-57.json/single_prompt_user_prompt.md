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
"name" : "Initial sum, 0"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Point"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Offset from nearest centre"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Unscaled distance"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Distance to nearest centre"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Squared distance"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Cumulative squared distance"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Point"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Offset from nearest centre"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Unscaled distance"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Distance to nearest centre"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Squared distance"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Cumulative squared distance"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Point"
},
"id" : "i_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Offset from nearest centre"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Unscaled distance"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Distance to nearest centre"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Squared distance"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Cumulative squared distance"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Point"
},
"id" : "i_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Offset from nearest centre"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Unscaled distance"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Distance to nearest centre"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Squared distance"
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Cumulative squared distance"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Point"
},
"id" : "i_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Offset from nearest centre"
},
"id" : "o_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Unscaled distance"
},
"id" : "o_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Distance to nearest centre"
},
"id" : "o_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Squared distance"
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Cumulative squared distance"
},
"id" : "o_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Point"
},
"id" : "i_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Offset from nearest centre"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Unscaled distance"
},
"id" : "o_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Distance to nearest centre"
},
"id" : "o_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Squared distance"
},
"id" : "o_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Cumulative squared distance"
},
"id" : "o_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Point"
},
"id" : "i_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Offset from nearest centre"
},
"id" : "o_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Unscaled distance"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Distance to nearest centre"
},
"id" : "o_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Squared distance"
},
"id" : "o_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Cumulative squared distance"
},
"id" : "o_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Point"
},
"id" : "i_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Offset from nearest centre"
},
"id" : "o_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Unscaled distance"
},
"id" : "o_49",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Distance to nearest centre"
},
"id" : "o_50",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Squared distance"
},
"id" : "o_51",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Cumulative squared distance"
},
"id" : "o_52",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Point"
},
"id" : "i_53",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Offset from nearest centre"
},
"id" : "o_54",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Unscaled distance"
},
"id" : "o_55",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Distance to nearest centre"
},
"id" : "o_56",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Squared distance"
},
"id" : "o_57",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Cumulative squared distance"
},
"id" : "o_58",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Uniform random draw, u"
},
"id" : "i_59",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.73"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Selection threshold, r"
},
"id" : "o_60",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7.3E-7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Zero"
},
"id" : "i_61",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "One"
},
"id" : "i_62",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Cumulative squared distance vs threshold"
},
"id" : "o_63",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Below threshold, −1 = yes"
},
"id" : "o_64",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Threshold reached, 1 = yes"
},
"id" : "o_65",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Chosen, 1 = yes"
},
"id" : "o_66",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Point index"
},
"id" : "i_67",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Chosen · index"
},
"id" : "o_68",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] No point chosen yet, 1 = yes"
},
"id" : "o_69",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Cumulative squared distance vs threshold"
},
"id" : "o_70",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Below threshold, −1 = yes"
},
"id" : "o_71",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Threshold reached, 1 = yes"
},
"id" : "o_72",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Chosen, 1 = yes"
},
"id" : "o_73",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Point index"
},
"id" : "i_74",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Chosen · index"
},
"id" : "o_75",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] No point chosen yet, 1 = yes"
},
"id" : "o_76",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Cumulative squared distance vs threshold"
},
"id" : "o_77",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Below threshold, −1 = yes"
},
"id" : "o_78",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Threshold reached, 1 = yes"
},
"id" : "o_79",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Chosen, 1 = yes"
},
"id" : "o_80",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Point index"
},
"id" : "i_81",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Chosen · index"
},
"id" : "o_82",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] No point chosen yet, 1 = yes"
},
"id" : "o_83",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Cumulative squared distance vs threshold"
},
"id" : "o_84",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Below threshold, −1 = yes"
},
"id" : "o_85",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Threshold reached, 1 = yes"
},
"id" : "o_86",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Chosen, 1 = yes"
},
"id" : "o_87",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Point index"
},
"id" : "i_88",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Chosen · index"
},
"id" : "o_89",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] No point chosen yet, 1 = yes"
},
"id" : "o_90",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Cumulative squared distance vs threshold"
},
"id" : "o_91",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Below threshold, −1 = yes"
},
"id" : "o_92",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Threshold reached, 1 = yes"
},
"id" : "o_93",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Chosen, 1 = yes"
},
"id" : "o_94",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Point index"
},
"id" : "i_95",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Chosen · index"
},
"id" : "o_96",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] No point chosen yet, 1 = yes"
},
"id" : "o_97",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Cumulative squared distance vs threshold"
},
"id" : "o_98",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Below threshold, −1 = yes"
},
"id" : "o_99",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Threshold reached, 1 = yes"
},
"id" : "o_100",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Chosen, 1 = yes"
},
"id" : "o_101",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Point index"
},
"id" : "i_102",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Chosen · index"
},
"id" : "o_103",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] No point chosen yet, 1 = yes"
},
"id" : "o_104",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Cumulative squared distance vs threshold"
},
"id" : "o_105",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Below threshold, −1 = yes"
},
"id" : "o_106",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Threshold reached, 1 = yes"
},
"id" : "o_107",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Chosen, 1 = yes"
},
"id" : "o_108",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Point index"
},
"id" : "i_109",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[6] Chosen · index"
},
"id" : "o_110",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] No point chosen yet, 1 = yes"
},
"id" : "o_111",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Cumulative squared distance vs threshold"
},
"id" : "o_112",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Below threshold, −1 = yes"
},
"id" : "o_113",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Threshold reached, 1 = yes"
},
"id" : "o_114",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Chosen, 1 = yes"
},
"id" : "o_115",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Point index"
},
"id" : "i_116",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[7] Chosen · index"
},
"id" : "o_117",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] No point chosen yet, 1 = yes"
},
"id" : "o_118",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Cumulative squared distance vs threshold"
},
"id" : "o_119",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Below threshold, −1 = yes"
},
"id" : "o_120",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Threshold reached, 1 = yes"
},
"id" : "o_121",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Chosen, 1 = yes"
},
"id" : "o_122",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Point index"
},
"id" : "i_123",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[8] Chosen · index"
},
"id" : "o_124",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Index of the second centre"
},
"id" : "o_125",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8"
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
"value" : "i_5"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
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
"id" : "op_3",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_4"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_11"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"id" : "op_8",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
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
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
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
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_17"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_13",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
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
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_23"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_25"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "o_26"
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
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_29"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_31"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_24",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_32"
}, {
"key" : "b",
"value" : "o_32"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_25",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_28"
}, {
"key" : "b",
"value" : "o_33"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_34"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_35"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_27",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_36"
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
"value" : "i_3"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_29",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_38"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_30",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_34"
}, {
"key" : "b",
"value" : "o_39"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_36",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_47"
}, {
"key" : "b",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_48"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_37",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_48"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_38",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_49"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "o_50"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_40",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_46"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_41",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_53"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_42",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_54"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_55"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_43",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_55"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_44",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_56"
}, {
"key" : "b",
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_45",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_52"
}, {
"key" : "b",
"value" : "o_57"
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
"value" : "i_59"
}, {
"key" : "b",
"value" : "o_58"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_47",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_63"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_48",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_63"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_64"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_49",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_64"
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
"id" : "op_50",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_65"
}, {
"key" : "b",
"value" : "i_62"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_51",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_66"
}, {
"key" : "b",
"value" : "i_67"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_52",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_66"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_69"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_53",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_70"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_54",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_70"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_71"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_55",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_71"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_72"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_56",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_72"
}, {
"key" : "b",
"value" : "o_69"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_73"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_57",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_73"
}, {
"key" : "b",
"value" : "i_74"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_58",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_69"
}, {
"key" : "b",
"value" : "o_73"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_59",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_77"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_60",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_77"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_78"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_61",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_78"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_62",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_79"
}, {
"key" : "b",
"value" : "o_76"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_80"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_63",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_80"
}, {
"key" : "b",
"value" : "i_81"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_64",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_76"
}, {
"key" : "b",
"value" : "o_80"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_65",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_28"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_84"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_66",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_84"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_85"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_67",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_85"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_68",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_86"
}, {
"key" : "b",
"value" : "o_83"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_87"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_69",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_87"
}, {
"key" : "b",
"value" : "i_88"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_70",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_83"
}, {
"key" : "b",
"value" : "o_87"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_71",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_34"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_91"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_72",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_91"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_92"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_73",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_92"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_74",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_93"
}, {
"key" : "b",
"value" : "o_90"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_94"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_75",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_94"
}, {
"key" : "b",
"value" : "i_95"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_76",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_90"
}, {
"key" : "b",
"value" : "o_94"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_77",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_40"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_98"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_78",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_98"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_99"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_79",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_99"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_80",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_100"
}, {
"key" : "b",
"value" : "o_97"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_101"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_81",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_101"
}, {
"key" : "b",
"value" : "i_102"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_82",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_97"
}, {
"key" : "b",
"value" : "o_101"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_83",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_46"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_105"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_84",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_105"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_106"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_85",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_106"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_86",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_107"
}, {
"key" : "b",
"value" : "o_104"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_108"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_87",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_108"
}, {
"key" : "b",
"value" : "i_109"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_88",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_104"
}, {
"key" : "b",
"value" : "o_108"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_89",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_52"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_112"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_90",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_112"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_113"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_91",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_113"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_92",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_114"
}, {
"key" : "b",
"value" : "o_111"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_115"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_93",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_115"
}, {
"key" : "b",
"value" : "i_116"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_94",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_111"
}, {
"key" : "b",
"value" : "o_115"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_95",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_58"
}, {
"key" : "b",
"value" : "o_60"
} ],
"resultId" : "o_119"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_96",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_119"
}, {
"key" : "b",
"value" : "i_61"
} ],
"resultId" : "o_120"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_97",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_62"
}, {
"key" : "b",
"value" : "o_120"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_98",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_121"
}, {
"key" : "b",
"value" : "o_118"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_122"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_99",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_122"
}, {
"key" : "b",
"value" : "i_123"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_100",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_68"
}, {
"key" : "b0",
"value" : "o_75"
}, {
"key" : "b1",
"value" : "o_82"
}, {
"key" : "b2",
"value" : "o_89"
}, {
"key" : "b3",
"value" : "o_96"
}, {
"key" : "b4",
"value" : "o_103"
}, {
"key" : "b5",
"value" : "o_110"
}, {
"key" : "b6",
"value" : "o_117"
}, {
"key" : "b7",
"value" : "o_124"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_125"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_11, i_17, i_23, i_29, i_35, i_41, i_47, i_53, i_59, i_61, i_62, i_67, i_74, i_81, i_88, i_95, i_102, i_109, i_116, i_123]
- **Leaf variable IDs**: [o_125]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, o_10, o_16, o_22, o_28, o_34, o_40, o_46, o_52, o_58, o_60, i_61, i_62, o_66, o_69, o_73, o_76, o_80, o_83, o_87, o_90, o_94, o_97, o_101, o_104, o_108, o_111, o_115]
- **Operations that consume the same variable more than once**: op_4 ("multiply") consumes [o_8] more than once; op_9 ("multiply") consumes [o_14] more than once; op_14 ("multiply") consumes [o_20] more than once; op_19 ("multiply") consumes [o_26] more than once; op_24 ("multiply") consumes [o_32] more than once; op_29 ("multiply") consumes [o_38] more than once; op_34 ("multiply") consumes [o_44] more than once; op_39 ("multiply") consumes [o_50] more than once; op_44 ("multiply") consumes [o_56] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
