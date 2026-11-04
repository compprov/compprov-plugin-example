## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "NRC 91A 7mm gauge block calibration, White 2025"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "computation precision"
},
"id" : "i_1",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 34,
"roundingMode" : "HALF_EVEN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "constant 1"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "constant 2"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "report",
"value" : "OFS-2024-0006"
}, {
"key" : "date",
"value" : "2024-05-02"
} ],
"name" : "lambda_vac1: TESA SG-L vacuum wavelength, nm"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "543.5153892"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "report",
"value" : "OFS-2024-0005"
}, {
"key" : "date",
"value" : "2024-04-30"
} ],
"name" : "lambda_vac2: TESA SG-O vacuum wavelength, nm"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "611.9703724"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "report",
"value" : "OFS-2024-0002"
}, {
"key" : "date",
"value" : "2024-05-08"
} ],
"name" : "lambda_vac3: Spectra 117A-1 HeNe vacuum wavelength, nm"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "632.9909778"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "T_air: air temperature, degC"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "P_air: air pressure, Pa"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "101325.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "h: relative humidity"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "xCO2: CO2 mole fraction, ppm"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "450.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Kelvin offset, K"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "273.15"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "T_K: air temperature, K"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "293.15"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "T_K^2, K^2"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "85936.9225"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Wexler A coefficient, K^-2"
},
"id" : "i_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000012378847"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Wexler B coefficient, K^-1"
},
"id" : "i_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.019121316"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Wexler C coefficient"
},
"id" : "i_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "33.93711047"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Wexler D coefficient, K"
},
"id" : "i_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-6343.1645"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "A*T_K^2"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.0638000152783575"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "B*T_K"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-5.60541378540"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "D/T_K"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-21.63794814941156404571038717380181"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "A*T_K^2 + B*T_K"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-4.5416137701216425"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "A*T_K^2 + B*T_K + C"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "29.3954966998783575"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ln(svp): Wexler/Sonntag exponent"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7.75754855046679345428961282619819"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "svp: saturation vapor pressure (Wexler 1976), Pa"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2339.1632301967884"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "nm-to-um conversion factor"
},
"id" : "i_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "lambda_vac3 in um"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.6329909778"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "sigma: vacuum wavenumber, um^-1"
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.579801347999560697688035830958727"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "sigma^2, um^-2"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.495772299141229083230759171428315"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sellmeier k0 [×10^-8]"
},
"id" : "i_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8342.54"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sellmeier k1 numerator"
},
"id" : "i_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2406147"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sellmeier k2 numerator"
},
"id" : "i_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "15998"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sellmeier UV resonance, um^-2"
},
"id" : "i_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "130"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sellmeier IR resonance, um^-2"
},
"id" : "i_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "38.9"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "130 - sigma^2"
},
"id" : "o_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "127.5042277008587709167692408285717"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "38.9 - sigma^2"
},
"id" : "o_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "36.40422770085877091676924082857168"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2406147 / (130 - sigma^2)"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "18871.11543975724998562805687371926"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "15998 / (38.9 - sigma^2)"
},
"id" : "o_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "439.4544537919866185168029170964151"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Ns partial"
},
"id" : "o_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "27213.65543975724998562805687371926"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "N_s: standard refractivity [×10^-8]"
},
"id" : "o_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "27653.10989354923660414485979081568"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "CO2 reference concentration, ppm"
},
"id" : "i_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "450"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "CO2 correction coefficient"
},
"id" : "i_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.34E-7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "xCO2 - 450"
},
"id" : "o_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "CO2 correction term"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "CO2 correction factor"
},
"id" : "o_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.0000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "N_s corrected for CO2 [×10^-8]"
},
"id" : "o_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "27653.10989354923660414485979081568"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "B&D denominator constant"
},
"id" : "i_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "96095.43"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "B&D pressure coefficient a"
},
"id" : "i_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.601"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "B&D pressure coefficient b"
},
"id" : "i_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00972"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "scale factor 1e-8"
},
"id" : "i_49",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E-8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "B&D thermal coefficient"
},
"id" : "i_50",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.003661"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "b*T"
},
"id" : "o_51",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.1944000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "a - b*T_air"
},
"id" : "o_52",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.4066000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "P*1e-8"
},
"id" : "o_53",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.001013250"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "P*1e-8*(a - b*T_air)"
},
"id" : "o_54",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0004119874500000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "pressure correction factor (1 + P*1e-8*(a-b*T))"
},
"id" : "o_55",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.0004119874500000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "0.003661*T_air"
},
"id" : "o_56",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.07322000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "thermal denominator factor (1 + 0.003661*T)"
},
"id" : "o_57",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.07322000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "B&D full denominator (96095.43*(1+0.003661*T))"
},
"id" : "o_58",
"valueClass" : "java.math.BigDecimal"
},
"value" : "103131.5373846000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "N_s_co2 * P"
},
"id" : "o_59",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2801951359.963876398914977918304399"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "N_s_co2 * P * pressCorr"
},
"id" : "o_60",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2803105728.759691948444682082823768"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "N_tp: dry air refractivity [×10^-8]"
},
"id" : "o_61",
"valueClass" : "java.math.BigDecimal"
},
"value" : "27179.90829814064748186375871911001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "water vapor enhancement alpha"
},
"id" : "i_62",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.00070"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "water vapor enhancement beta"
},
"id" : "i_63",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.7E-8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "water vapor enhancement gamma"
},
"id" : "i_64",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.24E-7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "T_air^2"
},
"id" : "o_65",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "beta*P_air"
},
"id" : "o_66",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0037490250"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "alpha + beta*P"
},
"id" : "o_67",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.0044490250"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "gamma*T_air^2"
},
"id" : "o_68",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0000496000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "f_enh: water vapor enhancement factor"
},
"id" : "o_69",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.0043994250000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "pv: partial pressure of water vapor, Pa. pv=h * f_enh * svp"
},
"id" : "o_70",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1174.7271016953984529033350000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Edlen water vapor coefficient W1"
},
"id" : "i_71",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.8020"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Edlen water vapor coefficient W2"
},
"id" : "i_72",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0384"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "scale factor 1e-3"
},
"id" : "i_73",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "W2*sigma^2"
},
"id" : "o_74",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.09583765628702319679606115218284730"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "W1 - W2*sigma^2"
},
"id" : "o_75",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.706162343712976803203938847817153"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "(W1-W2*sigma^2)*pv"
},
"id" : "o_76",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4353.729348442570376134612756495414"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "water vapor refractivity magnitude [×10^-8]"
},
"id" : "o_77",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.353729348442570376134612756495414"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "N_v: water vapor refractivity correction [×10^-8]"
},
"id" : "o_78",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-4.353729348442570376134612756495414"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "N_total = N_tp + N_v [×10^-8]"
},
"id" : "o_79",
"valueClass" : "java.math.BigDecimal"
},
"value" : "27175.55456879220491148762410635351"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "n-1 = N_total * 1e-8"
},
"id" : "o_80",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0002717555456879220491148762410635351"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "n: refractive index of air (Ciddor / Birch-Downs)"
},
"id" : "o_81",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.000271755545687922049114876241064"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Ciddor / Birch-Downs. Refractive index of air, n."
},
"id" : "i_82",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.0002718"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "lambda_air3: HeNe air wavelength, nm"
},
"id" : "o_83",
"valueClass" : "java.math.BigDecimal"
},
"value" : "632.8189776018878068940861873742717"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "lambda_air3/2: half-wavelength, nm"
},
"id" : "o_84",
"valueClass" : "java.math.BigDecimal"
},
"value" : "316.4094888009439034470430936871358"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "m: integer fringe order (method of exact fractions)"
},
"id" : "i_85",
"valueClass" : "java.math.BigDecimal"
},
"value" : "22123"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "f: fractional fringe order (observed)"
},
"id" : "i_86",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.23675"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "m+f: total fringe order"
},
"id" : "o_87",
"valueClass" : "java.math.BigDecimal"
},
"value" : "22123.23675"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "L_raw: raw interferometric length, nm"
},
"id" : "o_88",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7000002.030689755599428075449092936"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "White 2025, Appendix B"
} ],
"name" : "alpha: thermal expansion coefficient of tungsten carbide, K^-1"
},
"id" : "i_89",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00000423"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "T_part: part temperature, degC"
},
"id" : "i_90",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20.001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "T_ref: ISO 1 reference temperature, degC"
},
"id" : "i_91",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "deltaT: T_part - T_ref, K"
},
"id" : "o_92",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "alpha*deltaT"
},
"id" : "o_93",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.23E-9"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "1 + alpha*deltaT: thermal correction factor"
},
"id" : "o_94",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.00000000423"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "L_cal: thermally corrected length, nm"
},
"id" : "o_95",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7000002.001079747134860745068631984"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "White 2025"
} ],
"name" : "L_nom: nominal gauge block length (7 mm), nm"
},
"id" : "i_96",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "deltaL: length deviation from nominal, nm"
},
"id" : "o_97",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.001079747134860745068631984"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
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
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
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
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_14"
}, {
"key" : "b",
"value" : "o_13"
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
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_15"
}, {
"key" : "b",
"value" : "o_12"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_17"
}, {
"key" : "b",
"value" : "o_12"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
}, {
"key" : "b",
"value" : "o_19"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "b",
"value" : "i_16"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "o_20"
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
"value" : "Exp(a)"
} ],
"name" : "Exp_double"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_23"
} ],
"resultId" : "o_24"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_27"
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
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_32"
}, {
"key" : "b",
"value" : "o_28"
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
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_33"
}, {
"key" : "b",
"value" : "o_28"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_30"
}, {
"key" : "b",
"value" : "o_34"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_31"
}, {
"key" : "b",
"value" : "o_35"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_29"
}, {
"key" : "b",
"value" : "o_36"
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
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_38"
}, {
"key" : "b",
"value" : "o_37"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_10"
}, {
"key" : "b",
"value" : "i_40"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_41"
}, {
"key" : "b",
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_43"
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
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_39"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_48"
}, {
"key" : "b",
"value" : "i_7"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_24",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_47"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_25",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
}, {
"key" : "b",
"value" : "i_49"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_53"
}, {
"key" : "b",
"value" : "o_52"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_27",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
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
"id" : "op_28",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_50"
}, {
"key" : "b",
"value" : "i_7"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_29",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_30",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_46"
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
"id" : "op_31",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_45"
}, {
"key" : "b",
"value" : "i_8"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_32",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_59"
}, {
"key" : "b",
"value" : "o_55"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_33",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_60"
}, {
"key" : "b",
"value" : "o_58"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_34",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "i_7"
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
"id" : "op_35",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_63"
}, {
"key" : "b",
"value" : "i_8"
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
"id" : "op_36",
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
"resultId" : "o_67"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_37",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_64"
}, {
"key" : "b",
"value" : "o_65"
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
"id" : "op_38",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_67"
}, {
"key" : "b",
"value" : "o_68"
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
"value" : "(a*b0*...*bn)mc"
} ],
"name" : "multiplyBulk"
},
"id" : "op_39",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "b0",
"value" : "o_69"
}, {
"key" : "b1",
"value" : "o_24"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_40",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_72"
}, {
"key" : "b",
"value" : "o_28"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_74"
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
"value" : "i_71"
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
"id" : "op_42",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_75"
}, {
"key" : "b",
"value" : "o_70"
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
"id" : "op_43",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_76"
}, {
"key" : "b",
"value" : "i_73"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_77"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(-a)mc"
} ],
"name" : "negate"
},
"id" : "op_44",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_77"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_45",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_61"
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
"id" : "op_46",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_79"
}, {
"key" : "b",
"value" : "i_49"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_47",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_80"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_81"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_48",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
}, {
"key" : "b",
"value" : "i_82"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_49",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_83"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_84"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_50",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_85"
}, {
"key" : "b",
"value" : "i_86"
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
"id" : "op_51",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_87"
}, {
"key" : "b",
"value" : "o_84"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_88"
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
"value" : "i_90"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_53",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_89"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_54",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_93"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_55",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_88"
}, {
"key" : "b",
"value" : "o_94"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_95"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_56",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_95"
}, {
"key" : "b",
"value" : "i_96"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_97"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_9, i_10, i_11, i_14, i_15, i_16, i_17, i_25, i_29, i_30, i_31, i_32, i_33, i_40, i_41, i_46, i_47, i_48, i_49, i_50, i_62, i_63, i_64, i_71, i_72, i_73, i_82, i_85, i_86, i_89, i_90, i_91, i_96]
- **Leaf variable IDs**: [i_4, i_5, o_81, o_97]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_6, i_7, i_8, o_12, o_28, i_49]
- **Operations that consume the same variable more than once**: op_2 ("multiply") consumes [o_12] more than once; op_12 ("multiply") consumes [o_27] more than once; op_34 ("multiply") consumes [i_7] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
