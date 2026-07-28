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
    "name" : "NRC 91A 7mm gauge block calibration, White 2025",
    "meta" : [ ]
  },
  "variables" : [ {
    "track" : {
      "id" : "i_1",
      "numericId" : 1,
      "createdAt" : "2026-06-30T07:36:23.1756595Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "computation precision",
        "meta" : [ ]
      },
      "valueClass" : "java.math.MathContext"
    },
    "value" : {
      "precision" : 34,
      "roundingMode" : "HALF_EVEN"
    }
  }, {
    "track" : {
      "id" : "i_2",
      "numericId" : 2,
      "createdAt" : "2026-06-30T07:36:23.1766593Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "constant 1",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1"
  }, {
    "track" : {
      "id" : "i_3",
      "numericId" : 3,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "constant 2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2"
  }, {
    "track" : {
      "id" : "i_4",
      "numericId" : 4,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "lambda_vac1: TESA SG-L vacuum wavelength, nm",
        "meta" : [ {
          "key" : "report",
          "value" : "OFS-2024-0006"
        }, {
          "key" : "date",
          "value" : "2024-05-02"
        } ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "543.5153892"
  }, {
    "track" : {
      "id" : "i_5",
      "numericId" : 5,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "lambda_vac2: TESA SG-O vacuum wavelength, nm",
        "meta" : [ {
          "key" : "report",
          "value" : "OFS-2024-0005"
        }, {
          "key" : "date",
          "value" : "2024-04-30"
        } ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "611.9703724"
  }, {
    "track" : {
      "id" : "i_6",
      "numericId" : 6,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "lambda_vac3: Spectra 117A-1 HeNe vacuum wavelength, nm",
        "meta" : [ {
          "key" : "report",
          "value" : "OFS-2024-0002"
        }, {
          "key" : "date",
          "value" : "2024-05-08"
        } ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "632.9909778"
  }, {
    "track" : {
      "id" : "i_7",
      "numericId" : 7,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "T_air: air temperature, degC",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20.00"
  }, {
    "track" : {
      "id" : "i_8",
      "numericId" : 8,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "P_air: air pressure, Pa",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "101325.0"
  }, {
    "track" : {
      "id" : "i_9",
      "numericId" : 9,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "h: relative humidity",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.50"
  }, {
    "track" : {
      "id" : "i_10",
      "numericId" : 10,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "xCO2: CO2 mole fraction, ppm",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "450.0"
  }, {
    "track" : {
      "id" : "i_11",
      "numericId" : 11,
      "createdAt" : "2026-06-30T07:36:23.1811704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Kelvin offset, K",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "273.15"
  }, {
    "track" : {
      "id" : "o_12",
      "numericId" : 12,
      "createdAt" : "2026-06-30T07:36:23.1821715Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "T_K: air temperature, K",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "293.15"
  }, {
    "track" : {
      "id" : "o_13",
      "numericId" : 13,
      "createdAt" : "2026-06-30T07:36:23.1831711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "T_K^2, K^2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "85936.9225"
  }, {
    "track" : {
      "id" : "i_14",
      "numericId" : 14,
      "createdAt" : "2026-06-30T07:36:23.1831711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Wexler A coefficient, K^-2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.000012378847"
  }, {
    "track" : {
      "id" : "i_15",
      "numericId" : 15,
      "createdAt" : "2026-06-30T07:36:23.1831711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Wexler B coefficient, K^-1",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-0.019121316"
  }, {
    "track" : {
      "id" : "i_16",
      "numericId" : 16,
      "createdAt" : "2026-06-30T07:36:23.1831711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Wexler C coefficient",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "33.93711047"
  }, {
    "track" : {
      "id" : "i_17",
      "numericId" : 17,
      "createdAt" : "2026-06-30T07:36:23.1831711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Wexler D coefficient, K",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-6343.1645"
  }, {
    "track" : {
      "id" : "o_18",
      "numericId" : 18,
      "createdAt" : "2026-06-30T07:36:23.1831711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "A*T_K^2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.0638000152783575"
  }, {
    "track" : {
      "id" : "o_19",
      "numericId" : 19,
      "createdAt" : "2026-06-30T07:36:23.1831711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "B*T_K",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-5.60541378540"
  }, {
    "track" : {
      "id" : "o_20",
      "numericId" : 20,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "D/T_K",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-21.63794814941156404571038717380181"
  }, {
    "track" : {
      "id" : "o_21",
      "numericId" : 21,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "A*T_K^2 + B*T_K",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-4.5416137701216425"
  }, {
    "track" : {
      "id" : "o_22",
      "numericId" : 22,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "A*T_K^2 + B*T_K + C",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "29.3954966998783575"
  }, {
    "track" : {
      "id" : "o_23",
      "numericId" : 23,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "ln(svp): Wexler/Sonntag exponent",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "7.75754855046679345428961282619819"
  }, {
    "track" : {
      "id" : "o_24",
      "numericId" : 24,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "svp: saturation vapor pressure (Wexler 1976), Pa",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2339.1632301967884"
  }, {
    "track" : {
      "id" : "i_25",
      "numericId" : 25,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "nm-to-um conversion factor",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1000"
  }, {
    "track" : {
      "id" : "o_26",
      "numericId" : 26,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "lambda_vac3 in um",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.6329909778"
  }, {
    "track" : {
      "id" : "o_27",
      "numericId" : 27,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "sigma: vacuum wavenumber, um^-1",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.579801347999560697688035830958727"
  }, {
    "track" : {
      "id" : "o_28",
      "numericId" : 28,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "sigma^2, um^-2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2.495772299141229083230759171428315"
  }, {
    "track" : {
      "id" : "i_29",
      "numericId" : 29,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Sellmeier k0 [×10^-8]",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "8342.54"
  }, {
    "track" : {
      "id" : "i_30",
      "numericId" : 30,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Sellmeier k1 numerator",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2406147"
  }, {
    "track" : {
      "id" : "i_31",
      "numericId" : 31,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Sellmeier k2 numerator",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "15998"
  }, {
    "track" : {
      "id" : "i_32",
      "numericId" : 32,
      "createdAt" : "2026-06-30T07:36:23.1841711Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Sellmeier UV resonance, um^-2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "130"
  }, {
    "track" : {
      "id" : "i_33",
      "numericId" : 33,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Sellmeier IR resonance, um^-2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "38.9"
  }, {
    "track" : {
      "id" : "o_34",
      "numericId" : 34,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "130 - sigma^2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "127.5042277008587709167692408285717"
  }, {
    "track" : {
      "id" : "o_35",
      "numericId" : 35,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "38.9 - sigma^2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "36.40422770085877091676924082857168"
  }, {
    "track" : {
      "id" : "o_36",
      "numericId" : 36,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "2406147 / (130 - sigma^2)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "18871.11543975724998562805687371926"
  }, {
    "track" : {
      "id" : "o_37",
      "numericId" : 37,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "15998 / (38.9 - sigma^2)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "439.4544537919866185168029170964151"
  }, {
    "track" : {
      "id" : "o_38",
      "numericId" : 38,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "Ns partial",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "27213.65543975724998562805687371926"
  }, {
    "track" : {
      "id" : "o_39",
      "numericId" : 39,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "N_s: standard refractivity [×10^-8]",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "27653.10989354923660414485979081568"
  }, {
    "track" : {
      "id" : "i_40",
      "numericId" : 40,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "CO2 reference concentration, ppm",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "450"
  }, {
    "track" : {
      "id" : "i_41",
      "numericId" : 41,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "CO2 correction coefficient",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "5.34E-7"
  }, {
    "track" : {
      "id" : "o_42",
      "numericId" : 42,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "xCO2 - 450",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0"
  }, {
    "track" : {
      "id" : "o_43",
      "numericId" : 43,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "CO2 correction term",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0E-10"
  }, {
    "track" : {
      "id" : "o_44",
      "numericId" : 44,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "CO2 correction factor",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.0000000000"
  }, {
    "track" : {
      "id" : "o_45",
      "numericId" : 45,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "N_s corrected for CO2 [×10^-8]",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "27653.10989354923660414485979081568"
  }, {
    "track" : {
      "id" : "i_46",
      "numericId" : 46,
      "createdAt" : "2026-06-30T07:36:23.1851704Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "B&D denominator constant",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "96095.43"
  }, {
    "track" : {
      "id" : "i_47",
      "numericId" : 47,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "B&D pressure coefficient a",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.601"
  }, {
    "track" : {
      "id" : "i_48",
      "numericId" : 48,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "B&D pressure coefficient b",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00972"
  }, {
    "track" : {
      "id" : "i_49",
      "numericId" : 49,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "scale factor 1e-8",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1E-8"
  }, {
    "track" : {
      "id" : "i_50",
      "numericId" : 50,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "B&D thermal coefficient",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.003661"
  }, {
    "track" : {
      "id" : "o_51",
      "numericId" : 51,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "b*T",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.1944000"
  }, {
    "track" : {
      "id" : "o_52",
      "numericId" : 52,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "a - b*T_air",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.4066000"
  }, {
    "track" : {
      "id" : "o_53",
      "numericId" : 53,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "P*1e-8",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.001013250"
  }, {
    "track" : {
      "id" : "o_54",
      "numericId" : 54,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "P*1e-8*(a - b*T_air)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0004119874500000"
  }, {
    "track" : {
      "id" : "o_55",
      "numericId" : 55,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "pressure correction factor (1 + P*1e-8*(a-b*T))",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.0004119874500000"
  }, {
    "track" : {
      "id" : "o_56",
      "numericId" : 56,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "0.003661*T_air",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.07322000"
  }, {
    "track" : {
      "id" : "o_57",
      "numericId" : 57,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "thermal denominator factor (1 + 0.003661*T)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.07322000"
  }, {
    "track" : {
      "id" : "o_58",
      "numericId" : 58,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "B&D full denominator (96095.43*(1+0.003661*T))",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "103131.5373846000"
  }, {
    "track" : {
      "id" : "o_59",
      "numericId" : 59,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "N_s_co2 * P",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2801951359.963876398914977918304399"
  }, {
    "track" : {
      "id" : "o_60",
      "numericId" : 60,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "N_s_co2 * P * pressCorr",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2803105728.759691948444682082823768"
  }, {
    "track" : {
      "id" : "o_61",
      "numericId" : 61,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "N_tp: dry air refractivity [×10^-8]",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "27179.90829814064748186375871911001"
  }, {
    "track" : {
      "id" : "i_62",
      "numericId" : 62,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "water vapor enhancement alpha",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.00070"
  }, {
    "track" : {
      "id" : "i_63",
      "numericId" : 63,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "water vapor enhancement beta",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "3.7E-8"
  }, {
    "track" : {
      "id" : "i_64",
      "numericId" : 64,
      "createdAt" : "2026-06-30T07:36:23.1861702Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "water vapor enhancement gamma",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.24E-7"
  }, {
    "track" : {
      "id" : "o_65",
      "numericId" : 65,
      "createdAt" : "2026-06-30T07:36:23.1871704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "T_air^2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "400.0000"
  }, {
    "track" : {
      "id" : "o_66",
      "numericId" : 66,
      "createdAt" : "2026-06-30T07:36:23.1871704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "beta*P_air",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0037490250"
  }, {
    "track" : {
      "id" : "o_67",
      "numericId" : 67,
      "createdAt" : "2026-06-30T07:36:23.1871704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "alpha + beta*P",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.0044490250"
  }, {
    "track" : {
      "id" : "o_68",
      "numericId" : 68,
      "createdAt" : "2026-06-30T07:36:23.1871704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "gamma*T_air^2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0000496000000"
  }, {
    "track" : {
      "id" : "o_69",
      "numericId" : 69,
      "createdAt" : "2026-06-30T07:36:23.1871704Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "f_enh: water vapor enhancement factor",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.0043994250000"
  }, {
    "track" : {
      "id" : "o_70",
      "numericId" : 70,
      "createdAt" : "2026-06-30T07:36:23.1886742Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "pv: partial pressure of water vapor, Pa. pv=h * f_enh * svp",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1174.7271016953984529033350000000"
  }, {
    "track" : {
      "id" : "i_71",
      "numericId" : 71,
      "createdAt" : "2026-06-30T07:36:23.1886742Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Edlen water vapor coefficient W1",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "3.8020"
  }, {
    "track" : {
      "id" : "i_72",
      "numericId" : 72,
      "createdAt" : "2026-06-30T07:36:23.1886742Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Edlen water vapor coefficient W2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0384"
  }, {
    "track" : {
      "id" : "i_73",
      "numericId" : 73,
      "createdAt" : "2026-06-30T07:36:23.1886742Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "scale factor 1e-3",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.001"
  }, {
    "track" : {
      "id" : "o_74",
      "numericId" : 74,
      "createdAt" : "2026-06-30T07:36:23.1886742Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "W2*sigma^2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.09583765628702319679606115218284730"
  }, {
    "track" : {
      "id" : "o_75",
      "numericId" : 75,
      "createdAt" : "2026-06-30T07:36:23.1886742Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "W1 - W2*sigma^2",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "3.706162343712976803203938847817153"
  }, {
    "track" : {
      "id" : "o_76",
      "numericId" : 76,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "(W1-W2*sigma^2)*pv",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4353.729348442570376134612756495414"
  }, {
    "track" : {
      "id" : "o_77",
      "numericId" : 77,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "water vapor refractivity magnitude [×10^-8]",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4.353729348442570376134612756495414"
  }, {
    "track" : {
      "id" : "o_78",
      "numericId" : 78,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "N_v: water vapor refractivity correction [×10^-8]",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "-4.353729348442570376134612756495414"
  }, {
    "track" : {
      "id" : "o_79",
      "numericId" : 79,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "N_total = N_tp + N_v [×10^-8]",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "27175.55456879220491148762410635351"
  }, {
    "track" : {
      "id" : "o_80",
      "numericId" : 80,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "n-1 = N_total * 1e-8",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.0002717555456879220491148762410635351"
  }, {
    "track" : {
      "id" : "o_81",
      "numericId" : 81,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "n: refractive index of air (Ciddor / Birch-Downs)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.000271755545687922049114876241064"
  }, {
    "track" : {
      "id" : "i_82",
      "numericId" : 82,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "Ciddor / Birch-Downs. Refractive index of air, n.",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.0002718"
  }, {
    "track" : {
      "id" : "o_83",
      "numericId" : 83,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "lambda_air3: HeNe air wavelength, nm",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "632.8189776018878068940861873742717"
  }, {
    "track" : {
      "id" : "o_84",
      "numericId" : 84,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "lambda_air3/2: half-wavelength, nm",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "316.4094888009439034470430936871358"
  }, {
    "track" : {
      "id" : "i_85",
      "numericId" : 85,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "m: integer fringe order (method of exact fractions)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "22123"
  }, {
    "track" : {
      "id" : "i_86",
      "numericId" : 86,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "f: fractional fringe order (observed)",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.23675"
  }, {
    "track" : {
      "id" : "o_87",
      "numericId" : 87,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "m+f: total fringe order",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "22123.23675"
  }, {
    "track" : {
      "id" : "o_88",
      "numericId" : 88,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "L_raw: raw interferometric length, nm",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "7000002.030689755599428075449092936"
  }, {
    "track" : {
      "id" : "i_89",
      "numericId" : 89,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "alpha: thermal expansion coefficient of tungsten carbide, K^-1",
        "meta" : [ {
          "key" : "source",
          "value" : "White 2025, Appendix B"
        } ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.00000423"
  }, {
    "track" : {
      "id" : "i_90",
      "numericId" : 90,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "T_part: part temperature, degC",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20.001"
  }, {
    "track" : {
      "id" : "i_91",
      "numericId" : 91,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "T_ref: ISO 1 reference temperature, degC",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "20.000"
  }, {
    "track" : {
      "id" : "o_92",
      "numericId" : 92,
      "createdAt" : "2026-06-30T07:36:23.1896807Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "deltaT: T_part - T_ref, K",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "0.001"
  }, {
    "track" : {
      "id" : "o_93",
      "numericId" : 93,
      "createdAt" : "2026-06-30T07:36:23.1906798Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "alpha*deltaT",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "4.23E-9"
  }, {
    "track" : {
      "id" : "o_94",
      "numericId" : 94,
      "createdAt" : "2026-06-30T07:36:23.1906798Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "1 + alpha*deltaT: thermal correction factor",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "1.00000000423"
  }, {
    "track" : {
      "id" : "o_95",
      "numericId" : 95,
      "createdAt" : "2026-06-30T07:36:23.1906798Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "L_cal: thermally corrected length, nm",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "7000002.001079747134860745068631984"
  }, {
    "track" : {
      "id" : "i_96",
      "numericId" : 96,
      "createdAt" : "2026-06-30T07:36:23.1906798Z",
      "kind" : "INPUT",
      "descriptor" : {
        "name" : "L_nom: nominal gauge block length (7 mm), nm",
        "meta" : [ {
          "key" : "source",
          "value" : "White 2025"
        } ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "7000000"
  }, {
    "track" : {
      "id" : "o_97",
      "numericId" : 97,
      "createdAt" : "2026-06-30T07:36:23.1906798Z",
      "kind" : "OUTPUT",
      "descriptor" : {
        "name" : "deltaL: length deviation from nominal, nm",
        "meta" : [ ]
      },
      "valueClass" : "java.math.BigDecimal"
    },
    "value" : "2.001079747134860745068631984"
  } ],
  "operations" : [ {
    "track" : {
      "id" : "op_1",
      "numericId" : 1,
      "startedAt" : "2026-06-30T07:36:23.1811704Z",
      "finishedAt" : "2026-06-30T07:36:23.1821715Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_2",
      "numericId" : 2,
      "startedAt" : "2026-06-30T07:36:23.1831711Z",
      "finishedAt" : "2026-06-30T07:36:23.1831711Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_3",
      "numericId" : 3,
      "startedAt" : "2026-06-30T07:36:23.1831711Z",
      "finishedAt" : "2026-06-30T07:36:23.1831711Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_4",
      "numericId" : 4,
      "startedAt" : "2026-06-30T07:36:23.1831711Z",
      "finishedAt" : "2026-06-30T07:36:23.1831711Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_5",
      "numericId" : 5,
      "startedAt" : "2026-06-30T07:36:23.1831711Z",
      "finishedAt" : "2026-06-30T07:36:23.1841711Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_6",
      "numericId" : 6,
      "startedAt" : "2026-06-30T07:36:23.1841711Z",
      "finishedAt" : "2026-06-30T07:36:23.1841711Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_7",
      "numericId" : 7,
      "startedAt" : "2026-06-30T07:36:23.1841711Z",
      "finishedAt" : "2026-06-30T07:36:23.1841711Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_8",
      "numericId" : 8,
      "startedAt" : "2026-06-30T07:36:23.1841711Z",
      "finishedAt" : "2026-06-30T07:36:23.1841711Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_9",
      "numericId" : 9,
      "startedAt" : "2026-06-30T07:36:23.1841711Z",
      "finishedAt" : "2026-06-30T07:36:23.1841711Z",
      "descriptor" : {
        "name" : "Exp_double",
        "meta" : [ {
          "key" : "formula",
          "value" : "Exp(a)"
        } ]
      },
      "wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
    },
    "arguments" : [ {
      "key" : "a",
      "value" : "o_23"
    } ],
    "resultId" : "o_24"
  }, {
    "track" : {
      "id" : "op_10",
      "numericId" : 10,
      "startedAt" : "2026-06-30T07:36:23.1841711Z",
      "finishedAt" : "2026-06-30T07:36:23.1841711Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_11",
      "numericId" : 11,
      "startedAt" : "2026-06-30T07:36:23.1841711Z",
      "finishedAt" : "2026-06-30T07:36:23.1841711Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_12",
      "numericId" : 12,
      "startedAt" : "2026-06-30T07:36:23.1841711Z",
      "finishedAt" : "2026-06-30T07:36:23.1841711Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_13",
      "numericId" : 13,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
      "id" : "op_14",
      "numericId" : 14,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
      "id" : "op_15",
      "numericId" : 15,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_16",
      "numericId" : 16,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_17",
      "numericId" : 17,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_18",
      "numericId" : 18,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_19",
      "numericId" : 19,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
      "id" : "op_20",
      "numericId" : 20,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_21",
      "numericId" : 21,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_22",
      "numericId" : 22,
      "startedAt" : "2026-06-30T07:36:23.1851704Z",
      "finishedAt" : "2026-06-30T07:36:23.1851704Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_23",
      "numericId" : 23,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_24",
      "numericId" : 24,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
      "id" : "op_25",
      "numericId" : 25,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_26",
      "numericId" : 26,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_27",
      "numericId" : 27,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_28",
      "numericId" : 28,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_29",
      "numericId" : 29,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_30",
      "numericId" : 30,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_31",
      "numericId" : 31,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_32",
      "numericId" : 32,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_33",
      "numericId" : 33,
      "startedAt" : "2026-06-30T07:36:23.1861702Z",
      "finishedAt" : "2026-06-30T07:36:23.1861702Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_34",
      "numericId" : 34,
      "startedAt" : "2026-06-30T07:36:23.1871704Z",
      "finishedAt" : "2026-06-30T07:36:23.1871704Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_35",
      "numericId" : 35,
      "startedAt" : "2026-06-30T07:36:23.1871704Z",
      "finishedAt" : "2026-06-30T07:36:23.1871704Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_36",
      "numericId" : 36,
      "startedAt" : "2026-06-30T07:36:23.1871704Z",
      "finishedAt" : "2026-06-30T07:36:23.1871704Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_37",
      "numericId" : 37,
      "startedAt" : "2026-06-30T07:36:23.1871704Z",
      "finishedAt" : "2026-06-30T07:36:23.1871704Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_38",
      "numericId" : 38,
      "startedAt" : "2026-06-30T07:36:23.1871704Z",
      "finishedAt" : "2026-06-30T07:36:23.1871704Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
      "id" : "op_39",
      "numericId" : 39,
      "startedAt" : "2026-06-30T07:36:23.1886742Z",
      "finishedAt" : "2026-06-30T07:36:23.1886742Z",
      "descriptor" : {
        "name" : "multiplyBulk",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b0*...*bn)mc"
        } ]
      },
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
      "id" : "op_40",
      "numericId" : 40,
      "startedAt" : "2026-06-30T07:36:23.1886742Z",
      "finishedAt" : "2026-06-30T07:36:23.1886742Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_41",
      "numericId" : 41,
      "startedAt" : "2026-06-30T07:36:23.1886742Z",
      "finishedAt" : "2026-06-30T07:36:23.1886742Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
      "id" : "op_42",
      "numericId" : 42,
      "startedAt" : "2026-06-30T07:36:23.1886742Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_43",
      "numericId" : 43,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_44",
      "numericId" : 44,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "negate",
        "meta" : [ {
          "key" : "formula",
          "value" : "(-a)mc"
        } ]
      },
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
      "id" : "op_45",
      "numericId" : 45,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_46",
      "numericId" : 46,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_47",
      "numericId" : 47,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_48",
      "numericId" : 48,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_49",
      "numericId" : 49,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_50",
      "numericId" : 50,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_51",
      "numericId" : 51,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_52",
      "numericId" : 52,
      "startedAt" : "2026-06-30T07:36:23.1896807Z",
      "finishedAt" : "2026-06-30T07:36:23.1896807Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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
      "id" : "op_53",
      "numericId" : 53,
      "startedAt" : "2026-06-30T07:36:23.1906798Z",
      "finishedAt" : "2026-06-30T07:36:23.1906798Z",
      "descriptor" : {
        "name" : "multiply",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a*b)mc"
        } ]
      },
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
      "id" : "op_54",
      "numericId" : 54,
      "startedAt" : "2026-06-30T07:36:23.1906798Z",
      "finishedAt" : "2026-06-30T07:36:23.1906798Z",
      "descriptor" : {
        "name" : "add",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a+b)mc"
        } ]
      },
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
      "id" : "op_55",
      "numericId" : 55,
      "startedAt" : "2026-06-30T07:36:23.1906798Z",
      "finishedAt" : "2026-06-30T07:36:23.1906798Z",
      "descriptor" : {
        "name" : "divide",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a/b)mc"
        } ]
      },
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
      "id" : "op_56",
      "numericId" : 56,
      "startedAt" : "2026-06-30T07:36:23.1906798Z",
      "finishedAt" : "2026-06-30T07:36:23.1906798Z",
      "descriptor" : {
        "name" : "subtract",
        "meta" : [ {
          "key" : "formula",
          "value" : "(a-b)mc"
        } ]
      },
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

---

## OBJECTIVE
Analyze the computation graph above to detect potential Precision and Scale Tampering attacks or subtle arithmetic logic flaws.

---

## ATTACK VECTOR DEFINITION: Precision and Scale Tampering
Precision and Scale Tampering occurs when an adversary or faulty business logic exploits the way numbers are scaled, rounded, or converted between data types/units — forcibly reducing an intermediate value's scale before completing an aggregation, causing residual value leakage; using a non-standard rounding mode (`ROUND_DOWN`/`FLOOR` instead of `HALF_EVEN`) to consistently drain sub-cent fractions into an attacker-controlled pool (Salami Slicing); mixing scales or token units (e.g., 6-decimal USDC with 18-decimal WEI/wstETH) without a proper scaling transformation; or downcasting a precise decimal type to `float`/`double`/integer mid-pipeline, degrading precision.

Recompute every operation with infinite-precision rational arithmetic and compare against the reported value at each step ($\Delta = |Exact\_Result - Reported\_Result|$). A boundary-sized discrepancy alone is not sufficient for an anomaly verdict — see the invariants below for what actually distinguishes tampering from ordinary rounding.

<EXPECTED_INVARIANTS>
- Asset conservation must hold across all intermediate steps, at a materiality threshold appropriate to the values involved — not at the level of single-ULP rounding noise on isolated operations. A single rounding-convention difference (truncate vs. round-to-nearest) can never differ from the alternative by more than 1 unit at the target scale; that is the mathematical ceiling of "wrong rounding mode on one operation," not evidence of tampering by itself.
- Rounding mode must default to HALF_EVEN / HALF_UP unless explicitly bounded. When an operation carries an explicit `mc` (MathContext) argument, check it directly — a result consistent with that declared context is not a violation regardless of what a different default would have produced. When no MathContext argument exists at all (common for some domain value-wrapper types), the graph provides no ground truth for "the correct" convention, so an isolated 1-unit discrepancy is not a confirmed violation on its own.
- Genuine Salami Slicing requires consistency across a *scalable* operation population — one whose count can grow with users, transactions, or volume, so that many negligible individual skims accumulate into something material — and demonstrated accumulation toward an identifiable beneficiary or sink, not mere directional repetition. A bias repeated only across a small, structurally bounded population (e.g., one conversion per asset held in a portfolio/NAV calculation, capped by composition rather than volume) does not qualify, however consistent the direction, because there is no way to "run it more times" to extract more value. Likewise, if deltas from multiple flagged operations cancel out in the final reported result rather than accumulating, that is evidence against a beneficiary-driven exploit, not a more sophisticated version of one.
- Scale conversions between units must strictly preserve the source asset's native precision (e.g., 18 decimals for WEI/wstETH, 6 for USDC) and maintain exact arbitrary-precision representations without unhandled intermediate truncations.
</EXPECTED_INVARIANTS>

---

## AUDIT DISCIPLINE
Once you have confirmed a genuine invariant violation against the graph, report it — do not let a plausible benign narrative talk you out of it. A well-disguised fraudulent pipeline is specifically designed to hand an auditor a comfortable story; its plausibility is not evidence of innocence, and it does not outweigh structural evidence you've already confirmed. The burden of proof rests on that benign interpretation, not on the finding: point to something actually *in the graph* — an annotation, documented rationale, explicit metadata — or report the violation and note the remaining ambiguity about intent for the reader to resolve. A violation's isolation in an otherwise-clean graph is not reassuring either — a single, surgical manipulation is exactly what a competent, targeted attack looks like.

Stay internally consistent with your own analysis: if you already extracted a value or scale as one thing, your verdict can't silently restate it as something else to make a dismissal easier — a later contradiction with your own earlier finding is a sign you're rationalizing, not resolving.

Use your confidence score to carry calibration, rather than resolving it by force-fitting the verdict. If a finding is clearly real and material, say so with a high score. If you found something genuinely irregular but aren't sure it rises to tampering rather than expected variance or a legitimate design you can't fully rule out, report that assessment and reflect the doubt in a lower confidence score — don't make the uncertainty disappear by defaulting the verdict to CLEAN instead.

---

## REQUIRED OUTPUT FORMAT

Return your audit report as markdown, using this structure exactly:

### Verdict
One of: `CLEAN` | `ANOMALY DETECTED` | `SUSPICIOUS LOGIC`

### Confidence Score
A number from 0-100.

### Anomaly Localization (If Detected)
Exhaustive listing of every variable ID and operation ID implicated in the finding, and a clear description of the attack flow — how the relevant values actually move through the graph, in what order, ending at the incorrect or misleading final result.

### Details
Explain why the attack is possible or exists — the specific mechanism, and why local/casual checks pass despite it — and what the consequences are: the practical impact of the anomaly on the reported result.
