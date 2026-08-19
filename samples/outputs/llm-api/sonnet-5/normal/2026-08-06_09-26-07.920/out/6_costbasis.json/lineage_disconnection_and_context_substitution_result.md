# Summary
- **Verdict**: CLEAN
- **Confidence score**: 90.0

## Anomaly Localization (If Detected)
No lineage disconnection or context substitution was found. Full forward propagation from the nine root inputs (i_1, i_2, i_3, i_5, i_6, i_8, i_9, i_14, i_15) through every operation converges cleanly to the reported final output o_18, and — critically — every computed intermediate OUTPUT is consumed by its rightful `resultId` reference in the very next logical step:

- op_1(i_2,i_3)->o_4, op_2(i_5,i_6)->o_7, op_3(i_8,i_9)->o_10 — per-lot costs.
- op_4 addBulk(i_2,i_5,i_8)->o_11 = 325 total shares.
- op_5 addBulk(o_4,o_7,o_10)->o_12 = 13812.50 total cost.
- op_6 divide(o_12,o_11)->o_13 = 42.50 weighted-average cost/share.
- op_7 multiply(i_14,i_15)->o_16 = 5730.00 sale proceeds.
- op_8 multiply(i_14,**o_13**)->o_17 = 5100.00 cost basis of shares sold — this is the critical juncture the attack vector targets, and it correctly references `o_13`, the actual `resultId` of op_6, not a hardcoded or foreign stand-in.
- op_9 subtract(o_16,o_17)->o_18 = 630.00 realized gain/loss (sole leaf/terminal output).

One data point warrants explicit note because it superficially resembles the attack signature described: the weighted-average cost per share (o_13 = 42.50) is numerically identical to Lot 1's price per share (i_3 = 42.50). This coincidence is arithmetically explainable — (4250.00+5737.50+3825.00)/325 = 42.50 exactly — and is not a substitution, because op_8 unambiguously consumes `o_13` (verified against its `resultId`), never `i_3`. There is no name collision (confirmed empty per structural reference data) and no role duplication: each variable in the graph (lot shares/prices/costs, totals, weighted average, sale inputs/outputs, gain/loss) maps to exactly one unique quantity with no computed sibling being bypassed in favor of a hardcoded twin.

## Details
I independently walked every argument dictionary of every operation and cross-checked it against the emitting operation's `resultId`, rather than trusting variable names or values alone, specifically to defend against the disguised-substitution pattern (foreign INPUT masquerading under a computed variable's name/value/role). No operation in this graph substitutes a root INPUT or unrelated cached value for a variable's own computed upstream sibling. The multiply at op_8, which is the highest-risk juncture (feeding directly into the cost basis and hence the final realized gain/loss), correctly draws its price-per-share operand from `o_13`, the live output of the weighted-average division (op_6), which itself correctly derives from the three lot-cost computations and the total-shares sum — a fully connected chain back to the nine root inputs.

The sole leaf variable, o_18, is the intended final terminal output (realized gain/loss) and has no computed sibling elsewhere in the graph performing the same role that was bypassed — it is a legitimate terminus, not a masked orphan. The four variables consumed by more than one operation (i_2, i_5, i_8, i_14) are legitimately reused (once for their own lot's cost multiplication, once for the total-shares addBulk, or in i_14's case for both sale-proceeds and cost-basis multiplications) — this is expected fan-out, not evidence of a parallel injected path.

Given the coincidental numeric overlap between o_13 and i_3, this graph is precisely the kind of case an adversary could exploit for a disguised substitution (routing i_3 into op_8 instead of o_13, since the numbers match and casual review or even superficial replay would not flag it). Having explicitly verified the actual argument reference in op_8's `arguments` map points to `o_13` and not `i_3`, I am confident this specific graph does not contain that substitution. No other operation shows a mismatch between its stated arguments and the correct upstream `resultId`. Impact: the reported cost-basis and realized gain/loss figures are fully and correctly derived from the disclosed root inputs with no severed provenance.
