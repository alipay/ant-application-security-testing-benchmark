package com.alipay.xast.tools;

/*
 * This class is used to contain the results, per codeblock type, of how well a tool did with respect
 * to that codeblock.
 */
class CodeBlockSupportResults {

    boolean reported =
            false; // Used to track if this codeblock has been reported as an issue already, so its
    // not reported as a 'potential' issue later on.
    String name; // The name of the codeblock
    String type; // Source, Dataflow, or Sink
    // For sources, dangerous-source = true, for dataflows, propagate-danger = true.
    // For both sources and dataflows, there are situations where it might still be safe. We
    // try not to generate such test cases, rather than marking them as FPs.
    boolean truePositive; // Does this code block allow true/false positives?
    String vulnCat = "none"; // If a Sink, contains the vuln category
    boolean supported =
            false; // By default, not supported until support is verified. Works only for
    // truePositive codeblcoks.
    // To determine if any test case using this code block has a failure, just check to see
    // if the Used vs. Passed values differ.
    int numTPTestCasesUsed = 0;
    int numTPTestCasesPassed = 0;
    int numFPTestCasesUsed = 0;
    int numFPTestCasesPassed = 0;

    // SOURCE and DATAFLOW specific values
    // Note: These only count cases where the sink IS supported.
    int numTPTestCasesUsedIgnoringUnsupportedCodeblocks = 0;
    int numTPTestCasesPassedIgnoringUnsupportedCodeblocks = 0;

    // SINK specific values
    // These try to figure out if a particular sink is causing False Postives
    // i.e., if for every test case where the Source/Dataflow are TPs, so its ONLY the sink that is
    // causing it to be a FP, and the number of these that pass are ZERO, then this SINK is causing
    // the tool to always flag this test case as a TP
    int numFPSinksWhereSourceDataflowAreTruePositivesUsed = 0;
    int numFPSinksWhereSourceDataflowAreTruePositivesPassed = 0;

    CodeBlockSupportResults(String name, String type, boolean truePositive) {
        this.name = name;
        this.type = type;
        this.truePositive = truePositive;
    }

    // Alternate constructor for SINK CodeBlockTypes
    CodeBlockSupportResults(String name, String type, boolean truePositive, String vulnCategory) {
        this.name = name;
        this.type = type;
        this.truePositive = truePositive;
        if ("SINK".equals(type)) this.vulnCat = vulnCategory;
        else
            throw new RuntimeException(
                    "ERROR: Codeblock '"
                            + name
                            + "' is not of type SINK, so can't have a vuln category");
    }

    public void setVulnCategory(String category) {
        this.vulnCat = category;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            CodeBlockSupportResults otherResult = (CodeBlockSupportResults) object;
            if (this.name != null && this.type != null)
                result = this.name.equals(otherResult.name) && this.type.equals(otherResult.type);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Codeblock type: "
                + type
                // Optionally add the vuln type if this codeblock is a SINK
                + ("SINK".equals(type) ? " (" + vulnCat + ")" : "")
                + ", name: "
                + name
                + ", truePositive: "
                + truePositive
                + ", True Positive - used: "
                + numTPTestCasesUsed
                + ", passed: "
                + numTPTestCasesPassed
                + ", False Positive - used: "
                + numFPTestCasesUsed
                + ", passed: "
                + numFPTestCasesPassed
                + ", supported: "
                + supported;
    }

    public String toStringIgnoringUnsupportedSinks() {
        return "Codeblock type: "
                + type
                // Optionally add the vuln type if this codeblock is a SINK
                + ("SINK".equals(type) ? " (" + vulnCat + ")" : "")
                + ", name: "
                + name
                + ", truePositive: "
                + truePositive
                + ", Ignoring unsupported sinks: TPs - used: "
                + numTPTestCasesUsedIgnoringUnsupportedCodeblocks
                + ", passed: "
                + numTPTestCasesPassedIgnoringUnsupportedCodeblocks
                + ", FPs - used: "
                + numFPTestCasesUsed
                + ", passed: "
                + numFPTestCasesPassed
                + ", supported: "
                + supported;
    }

    public String toStringForFalsePositiveSinks() {
        return "Codeblock type: "
                + type
                // Optionally add the vuln type if this codeblock is a SINK
                + ("SINK".equals(type) ? " (" + vulnCat + ")" : "")
                + ", name: "
                + name
                + ", truePositive: "
                + truePositive
                /*                + ", True Positive - used: "
                                + numTPTestCasesUsed
                                + ", passed: "
                                + numTPTestCasesPassed
                */ + ", FPs (Including only w/ TP Sources/Dataflows) - used: "
                + numFPSinksWhereSourceDataflowAreTruePositivesUsed
                + ", passed: "
                + numFPSinksWhereSourceDataflowAreTruePositivesPassed
                + ", supported: "
                + supported;
    }
}
