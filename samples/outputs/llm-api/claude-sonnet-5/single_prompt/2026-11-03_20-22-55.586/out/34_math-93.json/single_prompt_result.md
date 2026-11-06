# Summary
- **Verdict**: CLEAN
- **Risk score**: 2.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: 0/100 — No omission, substitution, or double counting detected; single linear multiplication chain with one terminal leaf matching the expected computation.
- **Precision and Scale Tampering**: 2/100 — All intermediate products are exact integers within the declared MathContext precision (16 significant digits); no rounding, truncation, or scale manipulation occurs anywhere in the chain.
- **Semantic Type and Context Cast**: 0/100 — Variable naming and business meaning (factors and partial factorial products) remain fully consistent through every multiply step; no re-labeling or metadata suppression observed.

#### Anomaly Localization (If Detected)
None. The graph implements a straightforward sequential factorial computation: i_2 (seed value 1) is multiplied successively by i_3 (2), i_5 (3), i_7 (4), ..., i_33 (17), via operations op_1 through op_16, each strictly chaining the prior partial product (o_4, o_6, o_8, ..., o_32) into the next multiply operation, terminating at the single leaf o_34 ("Factorial 17!"). Every root factor (i_3, i_5, i_7, i_9, i_11, i_13, i_15, i_17, i_19, i_21, i_23, i_25, i_27, i_29, i_31, i_33) is consumed exactly once and in the correct sequential position. No variable is consumed by more than one operation, no operation repeats an argument, and no duplicate-named leaves exist.

#### Details
**Topological check**: Reconstructing n! = 1×2×3×...×17 confirms every factor from 2 through 17 is present exactly once in the multiplication chain, feeding forward into the terminal result o_34. There is no root variable left unconsumed that could represent a bypassed or substituted computation, and the single leaf o_34 is the expected terminal output with no other claimant on the final value. No evidence of Calculation Omission, Lineage Disconnection/Context Substitution, or Double Counting.

**Precision check**: The declared MathContext (i_1) specifies precision 16 with HALF_EVEN rounding — a standard, round-to-nearest convention appropriate for exact decimal arithmetic. Recomputing each step with exact rational arithmetic (1×2=2, 2×3=6, 6×4=24, 24×5=120, 120×6=720, 720×7=5040, 5040×8=40320, 40320×9=362880, 362880×10=3628800, 3628800×11=39916800, 39916800×12=479001600, 479001600×13=6227020800, 6227020800×14=87178291200, 87178291200×15=1307674368000, 1307674368000×16=20922789888000, 20922789888000×17=355687428096000) shows every reported value is an exact match against the recomputed exact result (Δ=0 at every step), since the maximum operand (355687428096000) only requires 15 significant digits, well within the 16-digit precision budget. No premature truncation, no salami slicing pattern, no downcasting to lower-precision types, and no scale conversion is present anywhere in the pipeline.

**Semantic check**: Each variable's descriptor name is self-consistent and domain-appropriate throughout the chain ("Factor, n" for multiplicands, "Partial product n!" for running results, culminating in "Factorial 17!"). No operation re-interprets a value's business meaning, no metadata is silently stripped in a way that creates ambiguity about domain context, and no cross-domain relabeling (e.g., gross/net, risk/discount) occurs. The arithmetic (`multiply`, formula `(a*b)mc`) is fully consistent with the stated business purpose at every step.

Overall, this is a clean, textbook factorial computation trace with no evidence of tampering across any of the three attack vectors. The low non-zero score reflects only the inherent baseline uncertainty of any audit, not any specific finding.