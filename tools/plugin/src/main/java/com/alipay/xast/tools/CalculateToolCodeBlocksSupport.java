/**
 * OWASP Benchmark Project
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author Dave Wichers
 * @created 2022
 */
package com.alipay.xast.tools;

import com.alipay.xast.CodeblockUtils;
import com.alipay.xast.helpers.Utils;
import com.alipay.xast.score.TestCaseResult;
import com.alipay.xast.score.TestSuiteResults;
import com.alipay.xast.score.TestSuiteResults.ToolType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mojo(
        name = "calculate-codeblock-support",
        requiresProject = false,
        defaultPhase = LifecyclePhase.COMPILE)
public class CalculateToolCodeBlocksSupport extends BenchmarkCrawler {

    @Parameter(property = "resultsCSVFile")
    String csvResultsFilenameParam = null;

    File csvResultsFile;

    // These are populated from the values mentioned in the Crawler XML file passed in.
    static ArrayList<String> SOURCES;
    static ArrayList<String> DATAFLOWS;
    static ArrayList<String> SINKS;

    /**
     * Load the CSV results file for the tool being analyzed.
     *
     * @param targetFileName The results file name
     * @throws RuntimeException If the file doesn't exist or can't be opened for some reason.
     */
    public void setScorecardResultsFile(String targetFileName)
            throws RuntimeException, IOException {
        File targetFile = new File(targetFileName);
        if (targetFile.exists()) {
            this.csvResultsFilenameParam = targetFileName;
            this.csvResultsFile = targetFile;
            System.out.println(
                    "Canonical path to CSV results file in setScorecardResultsFile() is: "
                            + this.csvResultsFile.getCanonicalPath());
        } else {
            throw new RuntimeException("Could not find CSV results file: '" + targetFileName + "'");
        }
    }

    /**
     * Process the command line arguments that make any configuration changes.
     *
     * @param args - args passed to main().
     * @return specified crawler file if valid command line arguments provided. Null otherwise.
     */
    @Override
    protected void processCommandLineArgs(String[] args) {

        // 1. Load testsuite-attack XML file so we have the codeblocks for every test case  - DONE
        // 1a. TestSuite instance has all the TestCases
        // 1b. Codeblocks are attributes of each instance of TestCase, created/read in from XML.

        // Example: -DcrawlerFile=data/benchmark-attack-http.xml

        // 2. Load the .csv results for the selected tool - DONE

        // Example:
        // -DresultsCSVFile=../../BenchmarkJavaBaseApp/scorecard/Benchmark_v1.3_Scorecard_for_Contrast_Assess_v4.8.0.csv

        // Do the work in run().

        // Set default attack crawler file, if it exists
        // This value can be changed by the -f parameter for other test suites with different names
        File defaultAttackCrawlerFile = new File(Utils.DATA_DIR, "benchmark-attack-http.xml");
        if (defaultAttackCrawlerFile.exists()) {
            setCrawlerFile(defaultAttackCrawlerFile.getPath());
        }

        RegressionTesting.isTestingEnabled = true;

        // Create the command line parser
        CommandLineParser parser = new DefaultParser();

        HelpFormatter formatter = new HelpFormatter();

        // Create the Options
        Options options = new Options();
        options.addOption(
                Option.builder("f")
                        .longOpt("file")
                        .desc("a TESTSUITE-attack-http.xml file")
                        .hasArg()
                        .required()
                        .build());
        options.addOption(
                Option.builder("r")
                        .longOpt("file")
                        .desc("a scorecard generated toolResults.csv file")
                        .hasArg()
                        .required()
                        .build());
        /*        options.addOption(Option.builder("h").longOpt("help").desc("Usage").build());
                options.addOption(
                        Option.builder("n")
                                .longOpt("name")
                                .desc("tescase name (e.g. BenchmarkTestCase00025)")
                                .hasArg()
                                .build());
                options.addOption(
                        Option.builder("t")
                                .longOpt("time")
                                .desc("testcase timeout (in seconds)")
                                .hasArg()
                                .type(Integer.class)
                                .build());
        */
        try {
            // Parse the command line arguments
            CommandLine line = parser.parse(options, args);

            if (line.hasOption("f")) {
                // Following throws a RuntimeException if the attack crawler file doesn't exist
                setCrawlerFile(line.getOptionValue("f"));
            }
            if (line.hasOption("r")) {
                setScorecardResultsFile(line.getOptionValue("r"));
            }
            /*            if (line.hasOption("h")) {
                            formatter.printHelp("BenchmarkCrawlerVerification", options, true);
                        }
                        if (line.hasOption("n")) {
                            selectedTestCaseName = line.getOptionValue("n");
                        }
                        if (line.hasOption("t")) {
                            maxTimeInSeconds = (Integer) line.getParsedOptionValue("t");
                        }
            */
        } catch (ParseException | IOException e) {
            formatter.printHelp("CalculateToolCodeBlocksSupport", options);
            throw new RuntimeException("Error parsing arguments: ", e);
        }
    }

    /** Calculate the code block support for the specified tool for the specified test suite. */
    @Override
    protected void run() {

        // Initialize the expected and actual results data structure from the
        //   TESTSUITE-attack-http.xml and results.csv already loaded.
        // Merge the .csv results with the codeblock details so you know which test cases
        //   pass/fail.
        //  - Probably can use the same structures used in ScoreCard generation, which is: TBD
        //  - Should use an array of: TestCaseResult - This class represents a single test case
        // result. It documents the expected result (real),
        //    and the actual result (result). Such an array is already contained in:
        // TestSuiteResults, but its contents have to be created using
        //    put(TestCaseResult) one test case at a time.
        // List<AbstractTestCaseRequest> testcases = this.testSuite.getTestCases() is already loaded

        // NOTE: The last 2 params are dummy values as I don't think we care about their type (yet)
        TestSuiteResults theToolResults =
                new TestSuiteResults(this.testSuite.getName(), false, ToolType.SAST);

        // Get all the TestCase info loaded from TESTSUITE-attack-http.xml file
        List<AbstractTestCaseRequest> theTestcases = this.testSuite.getTestCases();
        int testSuiteSize = theTestcases.size();

        try {
            java.io.Reader inReader = new java.io.FileReader(csvResultsFile);
            CSVParser recordParser = CSVFormat.Builder.create().setHeader().build().parse(inReader);

            List<CSVRecord> records = recordParser.getRecords();

            if (records.size() != testSuiteSize) {
                System.out.println(
                        "ERROR: Size of test suite from .xml file is: "
                                + testSuiteSize
                                + ", doesn't match size of test results: "
                                + records.size()
                                + " so can't calculate results. Aborting...");
                return;
            }

            // 2c. Read in the actual results.csv file so we can parse out the actual results per
            // test case
            Set<String> SOURCESSET = new HashSet<String>();
            Set<String> DATAFLOWSSET = new HashSet<String>();
            Set<String> SINKSSET = new HashSet<String>();

            // Initialize the arrays to contain the results
            Map<String, CodeBlockSupportResults> sourceCodeBlocksResults =
                    new HashMap<String, CodeBlockSupportResults>();
            Map<String, CodeBlockSupportResults> dataflowCodeBlocksResults =
                    new HashMap<String, CodeBlockSupportResults>();
            Map<String, CodeBlockSupportResults> sinkCodeBlocksResults =
                    new HashMap<String, CodeBlockSupportResults>();

            for (int i = 0; i < testSuiteSize; i++) {
                // Loop through each TestCaseRequest to construct a properly populated
                // TestCaseResult and then put(TestCaseResult)
                TestCaseResult theResult = new TestCaseResult(theTestcases.get(i));

                // Get whether this test case is a true or false positive and set that
                String truePositive = records.get(i).get(" real vulnerability").trim();
                theResult.setTruePositive(Boolean.parseBoolean(truePositive));

                // Get whether the tool passed/failed this test case and set that result
                String passFail = records.get(i).get(" pass/fail").trim();
                theResult.setPassed(passFail.equals("pass"));

                // While we are spinning through all the test cases, populate the lists of the
                // sources, dataflows, and sinks.
                String sourceCodeBlockFilename = theResult.getSource();
                if (SOURCESSET.add(sourceCodeBlockFilename)) {
                    // Get whether this is a dangerous source from the .xml file associated with the
                    // codeblock
                    File srcMetaDataFile =
                            new File(
                                    "codeblocks"
                                            + File.separator
                                            + "sources"
                                            + File.separator
                                            + sourceCodeBlockFilename.replace(".code", ".xml"));
                    if (srcMetaDataFile.exists()) {
                        Element emetadata =
                                CodeblockUtils.getSourceElementFromXMLFile(srcMetaDataFile);
                        boolean dangerousSource =
                                Boolean.parseBoolean(
                                        emetadata
                                                .getElementsByTagName("dangerous-source")
                                                .item(0)
                                                .getTextContent()
                                                .trim());

                        CodeBlockSupportResults newSource =
                                new CodeBlockSupportResults(
                                        sourceCodeBlockFilename, "SOURCE", dangerousSource);
                        sourceCodeBlocksResults.put(sourceCodeBlockFilename, newSource);
                    } else {
                        System.out.println(
                                "Source code block metadata file not found: "
                                        + srcMetaDataFile.getAbsolutePath());
                        System.exit(-1);
                    }
                }
                String dataflowCodeBlockFilename = theResult.getDataFlow();
                if (DATAFLOWSSET.add(dataflowCodeBlockFilename)) {
                    // Handle special case where dataflowCodeBlockFilename is 'Null', i.e., there is
                    // no dataflow
                    if ("".equals(dataflowCodeBlockFilename)) {
                        // This creates a dataflow for the 'empty' case. Which we calculate
                        // later, but we ignore it (for simplicity)
                        CodeBlockSupportResults newDataflow =
                                new CodeBlockSupportResults(
                                        dataflowCodeBlockFilename, "DATAFLOW", true);
                        dataflowCodeBlocksResults.put(dataflowCodeBlockFilename, newDataflow);
                    } else {
                        // Get whether this dataflow propagates danger from the .xml file associated
                        // with the codeblock
                        File dataflowMetaDataFile =
                                new File(
                                        "codeblocks"
                                                + File.separator
                                                + "dataflows"
                                                + File.separator
                                                + dataflowCodeBlockFilename.replace(
                                                        ".code", ".xml"));
                        if (dataflowMetaDataFile.exists()) {
                            Element emetadata =
                                    CodeblockUtils.getDataflowElementFromXMLFile(
                                            dataflowMetaDataFile);
                            boolean propagateDanger =
                                    Boolean.parseBoolean(
                                            emetadata
                                                    .getElementsByTagName("propagate-danger")
                                                    .item(0)
                                                    .getTextContent()
                                                    .trim());

                            CodeBlockSupportResults newDataflow =
                                    new CodeBlockSupportResults(
                                            dataflowCodeBlockFilename, "DATAFLOW", propagateDanger);
                            dataflowCodeBlocksResults.put(dataflowCodeBlockFilename, newDataflow);
                        } else {
                            System.out.println(
                                    "Dataflow code block metadata file not found: "
                                            + dataflowMetaDataFile.getAbsolutePath());
                            System.exit(-1);
                        }
                    }
                }
                String sinkCodeBlockFilename = theResult.getSink();
                if (SINKSSET.add(sinkCodeBlockFilename)) {
                    // Get whether this is a dangerous sink from the .xml file associated with the
                    // codeblock
                    File sinkMetaDataFile =
                            new File(
                                    "codeblocks"
                                            + File.separator
                                            + "sinks"
                                            + File.separator
                                            + sinkCodeBlockFilename.replace(".code", ".xml"));
                    if (sinkMetaDataFile.exists()) {
                        // For sinks, we also add the vuln category to the CodeBlockSupportResults
                        String vulnCategory = records.get(i).get(" category").trim();

                        Element emetadata =
                                CodeblockUtils.getSinkElementFromXMLFile(sinkMetaDataFile);
                        boolean vulnerableSink =
                                Boolean.parseBoolean(
                                        emetadata
                                                .getElementsByTagName("vulnerability")
                                                .item(0)
                                                .getTextContent()
                                                .trim());

                        CodeBlockSupportResults newSink =
                                new CodeBlockSupportResults(
                                        sinkCodeBlockFilename,
                                        "SINK",
                                        vulnerableSink,
                                        vulnCategory);
                        sinkCodeBlocksResults.put(sinkCodeBlockFilename, newSink);
                    } else {
                        System.out.println(
                                "Sink code block metadata file not found: "
                                        + sinkMetaDataFile.getAbsolutePath());
                        System.exit(-1);
                    }
                }

                theToolResults.put(theResult);
            }

            // 3. Calculate which codeblocks that tool seems to support and does not support

            // 3a. Loop through all the results in theToolResults to calculate the initial
            // statistics
            // across all of them.
            for (String tc : theToolResults.keySet()) {
                TestCaseResult theResult = theToolResults.get(tc).get(0); // Always only one.
                boolean passed = theResult.isPassed();
                CodeBlockSupportResults source = sourceCodeBlocksResults.get(theResult.getSource());
                CodeBlockSupportResults dataflow =
                        dataflowCodeBlocksResults.get(theResult.getDataFlow());
                CodeBlockSupportResults sink = sinkCodeBlocksResults.get(theResult.getSink());
                // For each codeblock (Sources, Dataflows, and Sinks), calculate:
                //   - # of uses in True Positives (across all test cases)
                //   - # of uses in False Positives (across all test cases)
                //   - # of True Positives the tool properly detected
                //   - # of False Positives the tool properly did NOT detect
                if (theResult.isTruePositive()) {
                    source.numTPTestCasesUsed++;
                    dataflow.numTPTestCasesUsed++;
                    sink.numTPTestCasesUsed++;
                    if (passed) {
                        // If a TP passes, we 'assume' that all code block elements are supported

                        // However, sources can be false positives, but the dataflow introduces
                        // taint, so we
                        // only 'know' if a source is supported if the source is also a true
                        // positive.
                        if (source.truePositive) source.supported = true;
                        source.numTPTestCasesPassed++;
                        dataflow.supported = true;
                        dataflow.numTPTestCasesPassed++;
                        sink.supported = true;
                        sink.numTPTestCasesPassed++;
                    }
                } else {
                    source.numFPTestCasesUsed++;
                    dataflow.numFPTestCasesUsed++;
                    sink.numFPTestCasesUsed++;
                    if (passed) {
                        source.numFPTestCasesPassed++;
                        dataflow.numFPTestCasesPassed++;
                        sink.numFPTestCasesPassed++;
                    }
                    // For a false positive test case, if only the SINK is a FP, count the # of
                    // pass/fails for this situation. If none pass, then this sink is causing False
                    // Positives.
                    if (source.truePositive && dataflow.truePositive) {
                        sink.numFPSinksWhereSourceDataflowAreTruePositivesUsed++;
                        if (passed) {
                            sink.numFPSinksWhereSourceDataflowAreTruePositivesPassed++;
                        }
                    }
                }
            }

            // 3b. For the True positives, calculate the sources/dataflows the tool seems
            // to support properly, based on the sinks it appears to support.

            // NOTE: I initially tried to ONLY count the sinks with NO dataflows, but with Benchmark
            // v1.3, there are many instances where there aren't any test cases like this, due to
            // the high % of test cases being discarded.

            // SOURCEs/DATAFLOWs: Calculate which sources cause TPs to NOT be detected.
            // Loop through them all again and calculate the number of TPs for each source that
            // pass/fail, ignoring any failures that are caused by unsupported SINKs.
            for (String tc : theToolResults.keySet()) {
                TestCaseResult theResult = theToolResults.get(tc).get(0); // Always only one.
                boolean passed = theResult.isPassed();
                CodeBlockSupportResults source = sourceCodeBlocksResults.get(theResult.getSource());
                CodeBlockSupportResults dataflow =
                        dataflowCodeBlocksResults.get(theResult.getDataFlow());
                CodeBlockSupportResults sink = sinkCodeBlocksResults.get(theResult.getSink());
                // For each SOURCE and DATAFLOW codeblock, ignoring any that use unsupported SINKs,
                // calculate:
                //   - # of uses in True Positives (across all test cases)
                //   - # of True Positives the tool properly detected
                if (theResult.isTruePositive() && sink.supported) {
                    source.numTPTestCasesUsedIgnoringUnsupportedCodeblocks++;
                    dataflow.numTPTestCasesUsedIgnoringUnsupportedCodeblocks++;
                    if (passed) {
                        source.numTPTestCasesPassedIgnoringUnsupportedCodeblocks++;
                        dataflow.numTPTestCasesPassedIgnoringUnsupportedCodeblocks++;
                    }
                }
            }

            // 3c. Calculate which sinks appear to be unsupported or always cause false positives
            String Always_FP_Output = "\n"; // Used to print all the FPs AFTER the Always FN values
            for (CodeBlockSupportResults sinkResult : sinkCodeBlocksResults.values()) {
                if (sinkResult.numTPTestCasesUsed > 0 && !sinkResult.supported) {
                    System.out.println("Always FN " + sinkResult);
                    sinkResult.reported = true;
                }
                if (sinkResult.numFPSinksWhereSourceDataflowAreTruePositivesUsed > 0
                        && sinkResult.numFPSinksWhereSourceDataflowAreTruePositivesPassed == 0) {
                    Always_FP_Output +=
                            "Always FP " + sinkResult.toStringForFalsePositiveSinks() + "\n";
                    sinkResult.reported = true;
                }
            }
            System.out.println(Always_FP_Output);

            // Given we know a number of Always FN or Always FP sinks, the converse is likely true
            // for non-FP Sinks.
            // If a sink doesn't look like its causing FPs, then if a test case with this sink IS a
            // FP, then its caused by the source or the dataflow. So the 1st thing to check for is
            // if the dataflow is 'null', and the test case is a FP, and the source is 'dangerous'
            // but the result is a FP, then its being caused by the source.  (Not sure if there are
            // any.) The same thing can be checked for in review for TPs.

            // NOTE: There aren't ALOT of 'null' dataflows normally, but for custom test cases we
            // can make sure there 'alot'.

            // Check Failures where the sink is 'supported' or hasn't been reported as a False
            // Positive and the dataflow is 'null'.
            boolean foundFPorFN = false;
            for (String tc : theToolResults.keySet()) {
                TestCaseResult theResult = theToolResults.get(tc).get(0); // Always only one.
                boolean passed = theResult.isPassed();
                CodeBlockSupportResults source = sourceCodeBlocksResults.get(theResult.getSource());
                CodeBlockSupportResults dataflow =
                        dataflowCodeBlocksResults.get(theResult.getDataFlow());
                CodeBlockSupportResults sink = sinkCodeBlocksResults.get(theResult.getSink());

                // Look for False Negatives where the sink is supported and the dataflow is null.
                // In that case, the source is causing False Negatives in this situation
                if (!passed
                        && theResult.isTruePositive()
                        && sink.supported
                        && dataflow.name.equals("")) {
                    if (!source.reported)
                        System.out.println("Source causing FNs: " + source.toString());
                    foundFPorFN = source.reported = true;
                }

                // Look for False Positives where the sink has not been reported and the dataflow is
                // null. In that case, the source is causing False Positives in this situation
                if (!passed
                        && !theResult.isTruePositive()
                        && sink.supported
                        && sink.truePositive
                        && dataflow.name.equals("")) {
                    if (!sink.reported && !source.truePositive) {
                        System.out.println("Source causing FP: " + source.toString());
                        System.out.println(" For: " + theResult.toString());
                        System.out.println("  " + dataflow.toString());
                        System.out.println("  " + sink.toString());
                        foundFPorFN = source.reported = true;
                    }
                }
            }
            // Print out a blank line for readability if any Source FNs or FPs reported
            if (foundFPorFN) System.out.println();

            // TODO: Repeat Failure checks with 'other' dataflows that appear to be supported.

            // Calculate which sources appear to be unsupported or always cause false positives
            // Then look for sources with 'mixed' results and list them as partially supported
            for (CodeBlockSupportResults sourceResult : sourceCodeBlocksResults.values()) {
                if (sourceResult.numTPTestCasesUsed > 0) {
                    if (!sourceResult.supported) {
                        if (sourceResult.truePositive
                                || (sourceResult.numFPTestCasesUsed
                                        > sourceResult.numFPTestCasesPassed)) {
                            System.out.println("Unsupported " + sourceResult);
                            sourceResult.reported = true;
                        }
                    } else if (sourceResult.numTPTestCasesPassedIgnoringUnsupportedCodeblocks
                            < sourceResult.numTPTestCasesUsedIgnoringUnsupportedCodeblocks) {
                        System.out.println(
                                "Source possibly causing FPs "
                                        + sourceResult.toStringIgnoringUnsupportedSinks());
                    }
                }
                if (sourceResult.numFPTestCasesUsed > 0 && sourceResult.numFPTestCasesPassed == 0)
                    System.out.println("Always FP: " + sourceResult);
            }

            // Calculate which dataflows appear to be unsupported or always cause false positives
            // Then look for dataflows with 'mixed' results and list them as partially supported
            for (CodeBlockSupportResults dataflowResult : dataflowCodeBlocksResults.values()) {
                if (dataflowResult.numTPTestCasesUsed > 0) {
                    if (!dataflowResult.supported) {
                        System.out.println("Unsupported " + dataflowResult);
                        dataflowResult.reported = true;
                    } else if (dataflowResult.numTPTestCasesPassedIgnoringUnsupportedCodeblocks
                            < dataflowResult.numTPTestCasesUsedIgnoringUnsupportedCodeblocks) {
                        System.out.println(
                                "Dataflow possibly causing FPs "
                                        + dataflowResult.toStringIgnoringUnsupportedSinks());
                    }
                }
                if (dataflowResult.numFPTestCasesUsed > 0
                        && dataflowResult.numFPTestCasesPassed == 0)
                    System.out.println("Always FP: " + dataflowResult);
            }

            /*
                        // Print out codeblock coordinates of suspect False Positives
                        for (int tc : theToolResults.keySet()) {
                            TestCaseResult theResult = theToolResults.get(tc).get(0); // Always only one.
                            boolean passed = theResult.isPassed();
                            CodeBlockSupportResults source = sourceCodeBlocksResults.get(theResult.getSource());
                            CodeBlockSupportResults dataflow =
                                    dataflowCodeBlocksResults.get(theResult.getDataFlow());
                            CodeBlockSupportResults sink = sinkCodeBlocksResults.get(theResult.getSink());

                            if (!theResult.isTruePositive() && !passed && !sink.reported && !source.reported) {
                                if (source.supported && dataflow.supported) {
                                    System.out.println(
                                            "False Positive possibly caused by SINK, since both source and dataflow supported. For: "
                                                    + theResult.toString());
                                    System.out.println("  " + source.toString());
                                    System.out.println("  " + dataflow.toString());
                                    System.out.println("  " + sink.toString());
                                }
                            }
                        }
            */

            // Print out codeblock coordinates of the rest of the False Positives, ignoring all with
            // sinks or sources already known to cause FPs
            int FPCount = 1;
            for (String tc : theToolResults.keySet()) {
                TestCaseResult theResult = theToolResults.get(tc).get(0); // Always only one.
                boolean passed = theResult.isPassed();
                CodeBlockSupportResults source = sourceCodeBlocksResults.get(theResult.getSource());
                CodeBlockSupportResults dataflow =
                        dataflowCodeBlocksResults.get(theResult.getDataFlow());
                CodeBlockSupportResults sink = sinkCodeBlocksResults.get(theResult.getSink());

                if (!theResult.isTruePositive() && !passed && !sink.reported && !source.reported) {
                    System.out.println(
                            "False Positive " + FPCount++ + " details: " + theResult.toString());
                    System.out.println("  " + source.toString());
                    System.out.println("  " + dataflow.toString());
                    System.out.println("  " + sink.toString());
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: Problem calculating code block support");
            e.printStackTrace();
        }
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (thisInstance == null) thisInstance = this;

        if (null == this.crawlerFile) {
            System.out.println(
                    "ERROR: An attack crawlerFile parameter must be specified to calculate a tool's code block support.");
        } else if (null == this.csvResultsFilenameParam) {
            System.out.println(
                    "ERROR: A resultsCSVFile parameter must be specified which specifies a tool's scorecard results.");
        } else {
            System.out.println(
                    "Have crawlerFile parameter and csvResultsFilenameParam value is: "
                            + this.csvResultsFilenameParam);
            String[] mainArgs = {"-f", this.crawlerFile, "-r", this.csvResultsFilenameParam};
            main(mainArgs);
        }
    }

    public static void main(String[] args) {
        // thisInstance can be set from execute() or here, depending on how this class is invoked
        // (via maven or command line)
        if (thisInstance == null) {
            thisInstance = new CalculateToolCodeBlocksSupport();
        }
        thisInstance.processCommandLineArgs(args);
        thisInstance.load();
        thisInstance.run();
    }
}
