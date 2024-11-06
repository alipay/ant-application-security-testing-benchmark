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
 * @author Juan Gama
 * @created 2017
 */
package com.alipay.xast.tools;

import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpMessage;

/**
 * Test all supported test cases to verify that the results are as expected and write the report to
 * a file.
 */
public class RegressionTesting {

    // Directly accessed by multiple classes, and set by some too
    public static boolean isTestingEnabled = false;

    // These are accessed by BenchmarkCrawlerVerification
    static int truePositives = 0;
    static int falsePositives = 0;
    static int failedTruePositives = 0;
    static int failedFalsePositives = 0;

    static int totalCount = 0;
    static int truePositivePassedCount = 0;
    static int falsePositivePassedCount = 0;
    static int truePositiveFailedCount = 0;
    static int falsePositiveFailedCount = 0;
    static int verifiedCount = 0;
    static int declaredUnverifiable = 0;
    static int undeclaredUnverifiable = 0;

    static SortedMultiset<String> undeclaredUnverifiableSinks = TreeMultiset.create();
    static SortedMultiset<String> nonDiscriminatorySinks = TreeMultiset.create();
    static SortedMultiset<String> failSinks = TreeMultiset.create();

    static Map<AbstractTestCaseRequest, String> failedTruePositivesList =
            new LinkedHashMap<AbstractTestCaseRequest, String>();
    static Map<AbstractTestCaseRequest, String> failedFalsePositivesList =
            new LinkedHashMap<AbstractTestCaseRequest, String>();

    // TODO: Make this flag configurable via command line parameter
    private static boolean isVerbosityOn = false;
    private static final String FILENAME_FAILEDTC = "failedTestCases.txt";

    /** The list of categories that will be included in the regression test. */
    private static final List<String> CATEGORIES_INCLUDED_IN_TEST =
            Arrays.asList(new String[] {"xss", "xxe"});

    // TODO: Since this is static, we might only be able to use (close) it once.
    static SimpleFileLogger ftcLogger;

    /**
     * Write a log file containing the details of all failed test cases.
     *
     * @param results
     * @param dataDir
     * @throws IOException
     * @throws LoggerConfigurationException
     */
    public static void genFailedTCFile(List<TestCaseVerificationResults> results, String dataDir)
            throws IOException, LoggerConfigurationException {

        final File FILE_FAILEDTC = new File(dataDir, FILENAME_FAILEDTC);
        SimpleFileLogger.setFile("FAILEDTC", FILE_FAILEDTC);

        try (SimpleFileLogger ftc = SimpleFileLogger.getLogger("FAILEDTC")) {

            ftcLogger = ftc;

            totalCount = results.size();

            for (TestCaseVerificationResults result : results) {
                AbstractTestCaseRequest requestTemplate = result.getRequestTemplate();

                String sink = null;
                String sinkMetaDataFilePath = requestTemplate.getSinkFile();
                if (sinkMetaDataFilePath != null) {
                    String sinkMetaDataFilename = new File(sinkMetaDataFilePath).getName();
                    sink = sinkMetaDataFilename.substring(0, sinkMetaDataFilename.indexOf('.'));
                }

                if (result.isUnverifiable()) {
                    if (result.isDeclaredUnverifiable()) {
                        declaredUnverifiable++;
                    } else {
                        undeclaredUnverifiable++;
                        if (sink == null) {
                            System.out.printf(
                                    "ERROR: No sink for request %s%n", requestTemplate.getName());
                        } else {
                            undeclaredUnverifiableSinks.add(sink);
                        }
                    }
                } else {
                    if (result.isPassed()) {
                        if (requestTemplate.isVulnerability()) truePositivePassedCount++;
                        else falsePositivePassedCount++;
                    } else {
                        if (requestTemplate.isVulnerability()) truePositiveFailedCount++;
                        else falsePositiveFailedCount++;
                    }
                    verifiedCount++;
                }
            }

            if (truePositiveFailedCount + falsePositiveFailedCount > 0) {
                for (TestCaseVerificationResults result : results) {
                    AbstractTestCaseRequest requestTemplate = result.getRequestTemplate();
                    if (isIncludedInTest(requestTemplate)) {
                        if (isVerbosityOn) {
                            System.out.println();
                            System.out.printf(
                                    "Test case request %s (category: %s, isVulnerability: %b, isNonverifiable: %b, isPassed: %b)%n",
                                    requestTemplate.getName(),
                                    requestTemplate.getCategory().toString(),
                                    requestTemplate.isVulnerability(),
                                    result.isUnverifiable(),
                                    result.isPassed());
                            System.out.println(requestTemplate.getFullURL());
                        }

                        if (!result.isUnverifiable() && !result.isPassed()) {
                            System.out.printf(
                                    "FAILURE: %s positive %s test case request %s%n",
                                    requestTemplate.isVulnerability() ? "True" : "False",
                                    requestTemplate.getCategory().toString(),
                                    requestTemplate.getName());
                        }
                    }
                }
            }

            if (truePositiveFailedCount + falsePositiveFailedCount > 0) {
                for (TestCaseVerificationResults result : results) {
                    AbstractTestCaseRequest requestTemplate = result.getRequestTemplate();
                    if (isIncludedInTest(requestTemplate)) {
                        if (!result.isUnverifiable() && !result.isPassed()) {
                            ftcLogger.print("FAILURE: ");
                            printTestCaseDetails(result, ftcLogger);
                        }
                    }
                }
                if (FILE_FAILEDTC.length() > 0) {
                    System.out.printf(
                            "Details of failed test cases written to: %s%n", FILE_FAILEDTC);
                }
            }
        }
    }

    private static void printHttpRequest(HttpMessage request, Logger out) {
        out.println(request.toString());
        for (Header header : request.getHeaders()) {
            out.printf("%s:%s%n", header.getName(), header.getValue());
        }
        if (request instanceof HttpPost) {
            HttpPost postHttpRequest = (HttpPost) request;
            out.println();
            try {
                HttpEntity entity = postHttpRequest.getEntity();
                if (entity != null) {
                    out.println(IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                System.out.println("ERROR: Could not parse HttpPost entities");
                e.printStackTrace();
            }
        }
    }

    private static void printTestCaseDetails(TestCaseVerificationResults result, Logger out) {
        AbstractTestCaseRequest requestTemplate = result.getRequestTemplate();
        ResponseInfo attackResponseInfo = result.getResponseToAttackValue();
        ResponseInfo safeResponseInfo = result.getResponseToSafeValue();
        out.printf(
                "%s positive %s test case request %s%n",
                requestTemplate.isVulnerability() ? "True" : "False",
                requestTemplate.getCategory().toString(),
                requestTemplate.getName());
        // Print out all the attributes of the request, including the templates used to create it
        out.println(requestTemplate.toString());
        out.println();
        out.println("Attack request:");
        printHttpRequest(result.getAttackRequest(), out);
        out.println();
        out.printf("Attack response: [%d]:%n", attackResponseInfo.getStatusCode());
        out.println(attackResponseInfo == null ? "null" : attackResponseInfo.getResponseString());
        out.println();
        out.println("Safe request:");
        printHttpRequest(result.getSafeRequest(), out);
        out.println();
        out.printf("Safe response: [%d]:%n", attackResponseInfo.getStatusCode());
        out.println(safeResponseInfo == null ? "null" : safeResponseInfo.getResponseString());
        out.println();
        String negatedAttackSuccessString =
                (requestTemplate.getAttackSuccessStringPresent() ? "" : "Failure ");
        out.printf(
                "Attack success %sindicator: -->%s<--%n",
                negatedAttackSuccessString, requestTemplate.getAttackSuccessString());
        out.printf("-----------------------------------------------------------%n%n");
    }

    /**
     * Print to the console a summary of the last crawl.
     *
     * @param results
     * @throws LoggerConfigurationException
     * @throws FileNotFoundException
     */
    public static void printCrawlSummary(List<TestCaseVerificationResults> results)
            throws FileNotFoundException, LoggerConfigurationException {

        int unverifiedCount = declaredUnverifiable + undeclaredUnverifiable;
        System.out.println("\n - Total number of test cases: " + totalCount);
        if (declaredUnverifiable > 0) {
            System.out.printf(" -- Declared not auto-verifiable: %d%n", declaredUnverifiable);
        }

        System.out.printf(
                " -- Test cases PASSED: %d%n", truePositivePassedCount + falsePositivePassedCount);
        System.out.printf("\tTP PASSED: %d%n", truePositivePassedCount);
        System.out.printf("\tFP PASSED: %d%n", falsePositivePassedCount);
        System.out.println(" - Problems:");
        System.out.printf(
                " -- Test cases FAILED: %d%n", truePositiveFailedCount + falsePositiveFailedCount);
        System.out.printf("\tTP FAILED: %d%n", truePositiveFailedCount);
        System.out.printf("\tFP FAILED: %d%n", falsePositiveFailedCount);
        if (failSinks.size() > 0) {
            System.out.printf(" -- Failed test cases by sink (total: %d)%n", failSinks.size());
            for (Multiset.Entry<String> sinkEntry : failSinks.entrySet()) {
                System.out.printf("\t%s (%d)%n", sinkEntry.getElement(), sinkEntry.getCount());
            }
        }

        if (undeclaredUnverifiableSinks.size() > 0) {
            System.out.printf(
                    " -- Unverifiable test cases by sink (total: %d)%n", undeclaredUnverifiable);
            System.out.println(
                    " (These sink .xml files are missing both the <attack-success-indicator> and <not-autoverifiable> attributes.)");
            for (Multiset.Entry<String> sinkEntry : undeclaredUnverifiableSinks.entrySet()) {
                System.out.printf("\t%s (%d)%n", sinkEntry.getElement(), sinkEntry.getCount());
            }
        }

        if (nonDiscriminatorySinks.size() > 0) {
            System.out.printf(
                    " -- Non-discriminatory test cases by sink (total: %d)%n",
                    nonDiscriminatorySinks.size());
            for (Multiset.Entry<String> sinkEntry : nonDiscriminatorySinks.entrySet()) {
                System.out.printf("\t%s (%d)%n", sinkEntry.getElement(), sinkEntry.getCount());
            }
        }

        if (totalCount - verifiedCount != unverifiedCount) {
            System.out.printf(
                    "ERROR: Unverifiable (%d) count does not equal total count minus verified count (%d - %d = %d)%n",
                    unverifiedCount, totalCount, verifiedCount, totalCount - verifiedCount);
        }
    }

    /**
     * Method to verify if the provided attackSuccessIndicator was included in the response, or the
     * status code wasn't a 200.
     *
     * <p>Has the following side effects: testCaseRequest.setPassed() - If test is verifiable, and
     * attackSuccessIndicator not found in response, setPassed set to false, otherwise true. Set to
     * true for all non-verifiable True Positives.
     * <!-- spotless:off -->
     *	If vulnerability
     * 		If attackValue response is verified and safeValue response is not verified --> pass
     *		If attackValue response is verified and safeValue response is verified --> fail and not
     *			discriminatory
     *		If attackValue response is not verified --> fail
	 *	Else
     *   	If attackValue response is not verified and safeValue response is not verified --> pass
     *   	If attackValue response is not verified and safeValue response is verified --> fail and
     *   		not discriminatory
     *   	If attackValue response is verified --> fail
	 * <!-- spotless:on -->
     *
     * @param result - The TestCaseVerificationResults for this test case.
     * @throws FileNotFoundException
     * @throws LoggerConfigurationException
     */
    public static void verifyTestCase(TestCaseVerificationResults result)
            throws FileNotFoundException, LoggerConfigurationException {

        SimpleFileLogger ndLogger = SimpleFileLogger.getLogger("NONDISCRIMINATORY");
        SimpleFileLogger uLogger = SimpleFileLogger.getLogger("UNVERIFIABLE");

        result.setUnverifiable(false); // Default
        result.setDeclaredUnverifiable(false); // Default
        if (result.getRequestTemplate().isUnverifiable()) {
            // Count this as "declared unverifiable" and return
            result.setUnverifiable(true);
            result.setDeclaredUnverifiable(true);
        } else if (result.getRequestTemplate().getAttackSuccessString() == null) {
            // Count this as "undeclared unverifiable" and return
            result.setUnverifiable(true);
            result.setDeclaredUnverifiable(false);
            uLogger.print("UNVERIFIABLE: ");
            printTestCaseDetails(result, uLogger);
        }

        List<String> reasons = new ArrayList<>();

        String sink = null;
        String sinkMetaDataFilePath = result.getRequestTemplate().getSinkFile();
        if (sinkMetaDataFilePath != null) {
            String sinkMetaDataFilename = new File(sinkMetaDataFilePath).getName();
            sink = sinkMetaDataFilename.substring(0, sinkMetaDataFilename.indexOf('.'));
        }

        if (!result.isUnverifiable()) {
            AbstractTestCaseRequest requestTemplate = result.getRequestTemplate();
            boolean isAttackValueVerified =
                    verifyResponse(
                            result.getResponseToAttackValue().getResponseString(),
                            requestTemplate.getAttackSuccessString(),
                            requestTemplate.getAttackSuccessStringPresent());
            boolean isSafeValueVerified =
                    verifyResponse(
                            result.getResponseToSafeValue().getResponseString(),
                            requestTemplate.getAttackSuccessString(),
                            requestTemplate.getAttackSuccessStringPresent());
            if (result.getRequestTemplate().isVulnerability()) {
                // True positive success?
                if (isAttackValueVerified) {
                    result.setPassed(true);
                    if (isSafeValueVerified) {
                        ndLogger.printf(
                                "Non-discriminatory true positive test %s: The attack-success-string: \"%s\" was found in the response to both the safe and attack requests.%n"
                                        + "\tTo verify that a test case is a true positive, the attack-success-string should be in the attack response, and not%n\tthe safe response. Please change the attack-success-string and/or the test case sink itself to ensure that the%n\tattack-success-string response is present only in a response to a successful attack.%n",
                                result.getRequestTemplate().getName(),
                                result.getRequestTemplate().getAttackSuccessString());
                        printTestCaseDetails(result, ndLogger);
                        nonDiscriminatorySinks.add(sink);
                    }
                } else {
                    result.setPassed(false);
                    failSinks.add(sink);
                }
            } else {
                // False positive success?
                if (isAttackValueVerified) {
                    result.setPassed(false);
                    failSinks.add(sink);
                } else {
                    result.setPassed(true);
                    if (isSafeValueVerified) {
                        ndLogger.printf(
                                "Non-discriminatory false positive test %s: The attack-success-string: \"%s\" was found in the response to the safe request.%n"
                                        + "\tTo verify that a test case is a false positive, the attack-success-string should not be in any response to this test%n\tcase. Please change the attack-success-string and/or the test case sink itself to ensure that the%n\tattack-success-string response is present only in a response to a successful attack.%n",
                                result.getRequestTemplate().getName(),
                                result.getRequestTemplate().getAttackSuccessString());
                        printTestCaseDetails(result, ndLogger);
                        nonDiscriminatorySinks.add(sink);
                    }
                }
            }
        }

        reasons = findErrors(result);
        boolean hasErrors = reasons.size() > 0;

        String compositeReason = "\t- " + String.join(", ", reasons);

        if (result.getRequestTemplate().isVulnerability()) {
            truePositives++;
            if (hasErrors) {
                failedTruePositives++;
                failedTruePositivesList.put(result.getRequestTemplate(), compositeReason);
            }
        } else {
            falsePositives++;
            if (hasErrors) {
                failedFalsePositives++;
                failedFalsePositivesList.put(result.getRequestTemplate(), compositeReason);
            }
        }
    }

    private static List<String> findErrors(TestCaseVerificationResults result) {
        List<String> reasons = new ArrayList<>();

        reasons.addAll(findErrors(result.getResponseToAttackValue(), "Attack value"));
        reasons.addAll(findErrors(result.getResponseToSafeValue(), "Safe value"));

        return reasons;
    }

    private static List<String> findErrors(ResponseInfo responseInfo, String prefix) {
        List<String> reasons = new ArrayList<>();

        if (responseInfo != null) {
            if (responseInfo.getStatusCode() != 200) {
                reasons.add(prefix + " response code: " + responseInfo.getStatusCode());
            }
            if (responseInfo.getResponseString().toLowerCase().contains("error")) {
                reasons.add(prefix + " response contains: error");
            } else if (responseInfo.getResponseString().toLowerCase().contains("exception")) {
                reasons.add(prefix + " response contains: exception");
            }
        }

        return reasons;
    }

    /**
     * Method to verify if the provided payload was included in the response.
     *
     * @param response - The response from this test case.
     * @param attackSuccessIndicator - The value to look for in the response to determine if the
     *     attack was successful.
     * @param attackSuccessStringPresent - boolean indicating if attack success indicator must be
     *     present (or absent) to pass.
     * @return true if the response passes the described checks. False otherwise.
     */
    public static boolean verifyResponse(
            String response, String attackSuccessIndicator, boolean attackSuccessStringPresent) {

        // Rip out any REFERER values
        attackSuccessIndicator = attackSuccessIndicator.replace("REFERER", "");

        return (response.contains(attackSuccessIndicator) == attackSuccessStringPresent);
    }

    private static boolean isIncludedInTest(AbstractTestCaseRequest testCaseRequestTemplate) {
        return CATEGORIES_INCLUDED_IN_TEST.contains(testCaseRequestTemplate.getCategory().getId())
                || (testCaseRequestTemplate.getAttackSuccessString() != null);
    }
}
