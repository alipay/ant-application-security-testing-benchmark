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
 * @created 2015
 */
package com.alipay.xast.score;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alipay.xast.score.TestSuiteResults.ToolType;
import com.alipay.xast.score.parsers.Reader;
import com.alipay.xast.score.util.BooleanExpressionEvaluatorUtil;
import com.alipay.xast.score.util.ScoreCardFormart;
import com.alipay.xast.score.util.ScoreCardHeader;
import com.alipay.xast.score.util.XastSupoortToolType;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mojo(name = "create-scorecard", requiresProject = false, defaultPhase = LifecyclePhase.COMPILE)
public class BenchmarkScore extends AbstractMojo {

    @Parameter(property = "configFile")
    String scoringConfigFile;

    private static final String START_MARKER = "evaluation information start";
    private static final String END_MARKER = "evaluation information end";
    static final String USAGE_MSG =
            "Usage: -cf /PATH/TO/scoringconfigfile.yaml or -cr scoringconfigfile.yaml (where file is a resource)";

    // The 1st line of a supplied expectedresults.csv file looks like:
    // # test name, category, real vulnerability, cwe, TESTSUITENAME version: x.y, YYYY-MM-DD

    // Prefixes for generated test suites and file names. Used by lots of other classes for
    // scorecard generation.
    public static String TESTSUITEVERSION; // Pulled from expected results file
    public static String TESTSUITE; // Pulled from expected results file
    public static final String TEST = "Test";
    public static String TESTCASENAME; // Set w/TESTSUITE. i.e., TESTSUITE + TEST;

    public static String TESTPACKAGE = "org.owasp.benchmark.testcode.";

    // The # of numbers in a test case name. Must match what is actually generated.
    public static final int TESTIDLENGTH = 5;

    private static final String HOMEFILENAME = "Scorecard_Home.html";

    // scorecard dir normally created under current user directory
    public static final String SCORECARDDIRNAME = "scorecard";

    // The values stored in this is pulled from the categories.xml config file
    //    public static Categories CATEGORIES;

    // This is the default project link. This is set to "" if includeProjectLink set to false.
    // TODO: Make this value configurable via .yaml file
    public static String PROJECTLINKENTRY =
            "            <p>\n"
                    + "                For more information, please visit the <a href=\"https://owasp.org/www-project-benchmark/\">OWASP Benchmark Project Site</a>.\n"
                    + "            </p>\n";

    // This is the Key Entry for Precision, which is added to the Key for tables that include
    // Precision. If includePrecision explicitly set to false via .yaml, then this default value set
    // to "".
    public static String PRECISIONKEYENTRY =
            "<tr>\n"
                    + "                    <th>Precision = TP / ( TP + FP )</th>\n"
                    + "                    <td>The percentage of reported vulnerabilities that are true positives. Defined at <a href=\"https://en.wikipedia.org/wiki/Precision_and_recall\">Wikipedia</a>.</td>\n"
                    + "                </tr>\n";

    // This is the Key Entry for F-Score, which is added to the Key for tables that also include
    // Precision. If includePrecision explicitly set to false via .yaml, then this default value set
    // to "".
    public static String FSCOREKEYENTRY =
            "<tr>\n"
                    + "                    <th>F-score = 2 * Precision * Recall / (Precision + Recall)</th>\n"
                    + "                    <td>The harmonic mean of the precision and recall. A value of 1.0 indicates perfect precision and recall. Defined at <a href=\"https://en.wikipedia.org/wiki/F-score\">Wikipedia</a>.</td>\n"
                    + "                </tr>\n";

    /*
     * The set of all the Tools. Each Tool includes the results for that tool.
     */
    private static Set<Tool> tools = new TreeSet<Tool>();

    private static HashMap<String, String> levelMap = new HashMap<>();
    private static HashMap<String, String> composeMap = new HashMap<>();

    public static Configuration config;

    /**
     * Process the command line arguments that make any configuration changes.
     *
     * @param args - args passed to main().
     */
    @VisibleForTesting
    static void loadConfigFromCommandLineArguments(String[] args) {
        if (args == null || args.length != 2) {
            System.out.println(USAGE_MSG);
            config = Configuration.fromDefaultConfig();
        } else {
            // -cf indicates use the specified configuration file to config Permute params
            if ("-cf".equalsIgnoreCase(args[0])) {
                config = Configuration.fromFile(args[1]);
            } else if ("-cr".equalsIgnoreCase(args[0])) {
                // -cr indicates use the specified configuration file resource to config Permute
                config = Configuration.fromResourceFile(args[1]);
            } else if (args[0] != null && args[1] != null && args[0].contains("=")) {
                System.out.println(USAGE_MSG);
                config = Configuration.fromDefaultConfig();
            } else {
                // pom settings for crawler forces creation of 2 args, but if none are provided,
                // they are null
                System.out.println(USAGE_MSG);
                throw new IllegalArgumentException();
            }
        }

        // TODO: move to html class (once this has been extracted, too)
        if (!config.includeProjectLink) {
            PROJECTLINKENTRY = "";
        }

        if (!config.includePrecision) {
            // These two values are both included or not included together (currently)
            PRECISIONKEYENTRY = "";
            FSCOREKEYENTRY = "";
        }
    }

    @Override
    public void execute() {
        // The Maven plugin invocation of this can have configFile be null, so we check for that
        // specifically
        if (null == scoringConfigFile) {
            String[] emptyMainArgs = {};
            try {
                main(emptyMainArgs); // Invoke scorecard generation with no params
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            String[] mainArgs = {"-cf", scoringConfigFile};
            try {
                main(mainArgs);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * This is the original main() method used to invoke the scorecard generator. e.g., mvn validate
     *
     * @param args - The command line arguments.
     */
    public static void main(String[] args) throws Exception {
        System.setProperty("directoryPath","/Users/curry/IdeaProjects/antcode/ant-application-security-testing-benchmark");
        System.setProperty("resultFile","/Users/curry/Desktop/xast-result/Benchmark_1.2-pmd-v5.2.3-11.xml");
        System.setProperty("lang","go");
        //System.setProperty("xlsxFile","/Users/curry/IdeaProjects/antcode/ant-application-security-testing-benchmark/results/xAST_v_1.0.0_pmd_v5.2.3_sast_python3.xlsx");
        try {
            loadConfigFromCommandLineArguments(args);
        } catch (RuntimeException e) {
            System.out.println("Error processing configuration for Scoring. Aborting.");
            System.exit(-1);
        }
        System.setProperty("nashorn.args","--no-deprecation-warning");
        Map<String, String> cons = new HashMap<>();
        BenchmarkScore.config = Configuration.fromDefaultConfig();
        if (StringUtils.isEmpty(System.getProperty("directoryPath"))) {
            System.out.println("靶场配置路径不能为空，配置参数:directoryPath;系统获取到的参数是:directoryPath="+ System.getProperty("directoryPath") + "\n" + "示例1：mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=xAST靶场绝对路径 -DresultFile=扫描结果绝对路径" + "\n" + "示例：mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=xAST靶场绝对路径 -DxlsxFile=手动填写的xlsx文件");
            System.exit(-1);
        }
        cons.put("directoryPath",System.getProperty("directoryPath"));
        if(StringUtils.isEmpty(System.getProperty("xlsxFile"))&&StringUtils.isEmpty(System.getProperty("resultFile"))){
            System.out.print("ERROR 参数:xlsxFile="+ System.getProperty("xlsxFile"));
            System.out.print(" 和参数:resultFile= "+ System.getProperty("resultFile"));
            System.out.println("不能同时为空！" + "\n" + "示例1：mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=xAST靶场绝对路径 -DresultFile=扫描结果绝对路径" + "\n" + "示例：mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=xAST靶场绝对路径 -DxlsxFile=手动填写的xlsx文件");
            System.exit(-1);
        }
        if(StringUtils.isNotEmpty(System.getProperty("xlsxFile"))&&StringUtils.isNotEmpty(System.getProperty("resultFile"))){
            System.out.print("ERROR 参数:xlsxFile="+ System.getProperty("xlsxFile"));
            System.out.print(" 和参数:resultFile= "+ System.getProperty("resultFile"));
            System.out.println("不能同时填写！");
            System.exit(-1);
        }
        if(StringUtils.isNotEmpty(System.getProperty("xlsxFile"))){
            cons.put("xlsxFile",System.getProperty("xlsxFile"));
        }else if(StringUtils.isNotEmpty(System.getProperty("resultFile"))){
            cons.put("resultFile",System.getProperty("resultFile"));
        }
        List<String> langs= Lists.newArrayList("java","python3","python2","js","go");
        if(StringUtils.isEmpty(System.getProperty("lang"))|| !langs.contains(System.getProperty("lang"))){
            System.out.println("请指定语言类型：lang=java/python3/python2/js/go");
            System.exit(-1);
        }else {
            cons.put("lang",System.getProperty("lang"));
        }
        mainChange(cons);
    }

    public static void mainChange(Map<String, String> cons) throws Exception {
        /**
         * 1 读取靶场数据 - 预期结果
         */
        Map<String, List<TestCaseResult>> expectedResultsMap = readExpectedResultsNew(cons.get("directoryPath"),cons.get("lang"));
        if (expectedResultsMap == null) {
            System.out.println("Couldn't read expected results file: " + expectedResultsMap);
            System.exit(-1);
        }

        /**
         *  读取实际结果数据
         */
        File rawToolResultsFile = null;
        if (StringUtils.isNotEmpty(cons.get("xlsxFile"))) {
            /**
             * 2a 从csv直接读取结果转换html
             */
            xlsxToHtml(cons.get("xlsxFile"), new File(cons.get("directoryPath")));
            System.exit(0);
        }else if(StringUtils.isNotEmpty(cons.get("resultFile"))){
            /**
             * 2b从扫描结果文件中解析成标准表格数据
             */
            rawToolResultsFile = new File(cons.get("resultFile"));
        }
        if(rawToolResultsFile == null){
            System.out.println("Error Couldn't read act results file: " + rawToolResultsFile);
            System.exit(-1);
        }
        File scoreCardDir = new File(cons.get("directoryPath"), "results");
        try {
            if (!scoreCardDir.exists()) {
                scoreCardDir.mkdir();
            }
//            else {
//                System.out.println(
//                        "Deleting previously generated scorecard files in: "
//                                + scoreCardDir.getAbsolutePath());
//                FileUtils.cleanDirectory(scoreCardDir);
//            }

            // Step 2: Now copy the entire /content directory, that either didn't exist, or was just
            // deleted with everything else

        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(
                    "Error dealing with scorecard directory: '"
                            + scoreCardDir.getAbsolutePath()
                            + "' for some reason!");
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            if (!rawToolResultsFile.isDirectory()) {
                processNew(rawToolResultsFile, expectedResultsMap, tools, scoreCardDir,cons.get("lang"));
            }
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Error during processing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processNew(
            File rawToolResultsFile,
            Map<String, List<TestCaseResult>> expectedResults,
            Set<Tool> tools,
            File scoreCardDir,String lang) {

        try {
            String resultsFileName = rawToolResultsFile.getName();
            // If the filename starts with a . ignore it
            if (resultsFileName.startsWith(".")) return;

            // Figure out the actual results for this tool from the raw results file for this tool
            System.out.println("\nAnalyzing results from " + resultsFileName);
            // TestSuiteResults rawToolResults = readActualResults(rawToolResultsFile);
            // TODO  读取reader返回的实际集合数据
            TestSuiteResults rawToolResults = readActualResultsNew(rawToolResultsFile);
            // System.out.println("Computed actual results for tool: " + actualResults.getTool());

            if (expectedResults != null && rawToolResults != null) {
                // note: side effect is that "pass/fail" value is set for each expected result so it
                // can be used to produce scorecard for this tool
                Set<String> keys = expectedResults.keySet();
                for (String key : keys) {
                    if (!StringUtils.isNotEmpty(key)) {
                        continue;
                    }
                }
                String toolType = rawToolResults.toolType.name().toLowerCase(Locale.ROOT);
                TestSuiteResults actualResults =
                        analyzeNew(
                                expectedResults.get(toolType+"-"+lang), rawToolResults);
                // Produce a .csv results file of the actual results, except if its a commercial
                // tool, and we are in showAveOnly mode.
                String actualResultsFileName = produceResultsFileNew(actualResults, scoreCardDir, toolType,lang);

                xlsxToHtml(scoreCardDir.getAbsolutePath()+"/"+actualResultsFileName,scoreCardDir);

            } else {
                if (expectedResults == null) {
                    System.out.println("Error!!: expected results were null.");
                } else
                    System.out.println(
                            "Error!!: actual results were null for file: " + rawToolResultsFile);
            }
        } catch (Exception e) {
            System.out.println("Error processing " + rawToolResultsFile + ". Continuing.");
            e.printStackTrace();
        }
    }
    private static Map<String,String> buildEvResMap(String level, String result) {
        Map<String,String> resMap = Maps.newHashMap();
        resMap.put(level,result);
        return resMap;
    }

    private static void xlsxToHtml(String fileName, File scoreCardDir) throws Exception {
        String accuracy = null;
        //String precision = null;
        String recall = null;
        String evaluateAccuracy = null;
        String toolName = "工具名";
        String toolTypeNew = "工具类型";
        String levelTable = null;
        StringBuilder levelTableBuilder = new StringBuilder();
        double TP = 0;
        double FN = 0;
        double FP = 0;
        double TN = 0;
        double evaluateValue = 0;
        double cases = 0;
        HashSet<String> oneSet = new HashSet<>();
        HashSet<String> onePlusSet = new HashSet<>();
        HashSet<String> twoSet = new HashSet<>();
        HashSet<String> twoPlusSet = new HashSet<>();
        HashSet<String> threeSet = new HashSet<>();
        HashSet<String> threePlusSet = new HashSet<>();
        HashSet<String> fourSet = new HashSet<>();
        HashSet<String> fourPlusSet = new HashSet<>();
        HashSet<String> oneFinishSet = new HashSet<>();
        HashSet<String> onePlusFinishSet = new HashSet<>();
        HashSet<String> twoFinishSet = new HashSet<>();
        HashSet<String> twoPlusFinishSet = new HashSet<>();
        HashSet<String> threeFinishSet = new HashSet<>();
        HashSet<String> threePlusFinishSet = new HashSet<>();
        HashSet<String> fourFinishSet = new HashSet<>();
        HashSet<String> fourPlusFinishSet = new HashSet<>();
        String oneFinishRate = null;
        String onePlusFinishRate = null;
        String twoFinishRate = null;
        String twoPlusFinishRate = null;
        String threeFinishRate = null;
        String threePlusFinishRate = null;
        String fourFinishRate = null;
        String fourPlusFinishRate = null;
        String oneColour = "#A8F7A9";
        String onePlusColour = "#76F376";
        String twoColour = "#9FF6C6";
        String twoPlusColour = "#52F69B";
        String threeColour = "#9AF6ED";
        String threePlusColour = "#5FF2E2";
        String fourColour = "#8AC3F6";
        String fourPlusColour = "#4AA2F0";
        HashSet<String> appraiseList = new HashSet<>();
        Map<String,Boolean> booleanMap = new HashMap();
        Map<String,Map<String,String>> evaluateMap = new HashMap();
        Map<String,String> colourMap = new HashMap();
        //HashSet<String> evaluateSet = new HashSet<>();
        //HashSet<String> evaluateSet2 = new HashSet<>();
        //工具名和工具类型赋值
        String[] fileNameParts = fileName.split("_");
        if (fileNameParts.length >= 2){
            toolName = fileNameParts[fileNameParts.length-4];
            toolTypeNew = fileNameParts[fileNameParts.length-2];
            // 去除扩展名 ".csv"
            toolTypeNew = toolTypeNew.replace(".xlsx", "");
        }
        // 检查文件名中是否指定出工具类型
        if (!XastSupoortToolType.isSupportToolType(toolTypeNew.toLowerCase())){
            throw new Exception("当前处理的文件名未标名工具类型，请根据工具类型处理为xAST_v_xAST版本_工具名_工具类型_语言.xlsx，例如:xAST_v_1.0.0_alipayscanner_sast_java.xlsx");
        }

        try {
            ExcelReaderSheetBuilder sheet = EasyExcel.read(fileName, ScoreCardFormart.class, null).sheet();
            // 标题
            sheet.head(ScoreCardHeader.getHeader());
            List<LinkedHashMap> data = sheet.doReadSync();
            for (int i = 0; i < data.size(); i++) {
                cases++;
                String hasVuln = (String) data.get(i).get(4);
                hasVuln = hasVuln.toUpperCase();
                String scanFindVuln = (String) data.get(i).get(5);
                scanFindVuln = scanFindVuln.toUpperCase();
                appraiseList.add((String) data.get(i).get(2));
                //处理获取到的列值
                if (StringUtils.equalsIgnoreCase(hasVuln, "TRUE")) {
                    if (StringUtils.equalsIgnoreCase(scanFindVuln, "TRUE")) {
                        TP++;
                    }
                    else if (StringUtils.equalsIgnoreCase(scanFindVuln, "FALSE")) {
                        FN++;
                    }
                }
                else if (StringUtils.equalsIgnoreCase(hasVuln, "FALSE")) {
                    if (StringUtils.equalsIgnoreCase(scanFindVuln, "TRUE")) {
                        FP++;
                    }
                    else if (StringUtils.equalsIgnoreCase(scanFindVuln, "FALSE")) {
                        TN++;
                    }
                }
                if (StringUtils.equalsIgnoreCase(scanFindVuln, "TRUE")) {
                    booleanMap.put((String) data.get(i).get(0), true);
                }else {
                    booleanMap.put((String) data.get(i).get(0), false);
                }
            }
            //准确率
            if ((TP + FN + FP + TN) != 0){
                double accuracyValue = (TP + TN) / (TP + FN + FP + TN);
                accuracy = String.format("%.2f", accuracyValue * 100) + "%";
            }
            //精确率
            //double precisionValue = TP / (TP + FP);
            //precision = String.format("%.2f", precisionValue) + "%";
            //召回率
            if ((TP + FN) != 0){
                double recallValue = TP / (TP + FN);
                recall = String.format("%.2f", recallValue * 100) + "%";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        // 得到所有case是否被识别成功后，再计算评价项达成率
        try{
            ExcelReaderSheetBuilder sheet = EasyExcel.read(fileName, ScoreCardFormart.class, null).sheet();
            // 标题
            sheet.head(ScoreCardHeader.getHeader());
            List<LinkedHashMap> data = sheet.doReadSync();
            Boolean result = false;
            Set<String> levelList = new HashSet<>();
            for (int i = 0; i < data.size(); i++) {
                String evaluateFormula = (String) data.get(i).get(3);
                String evaluate = (String) data.get(i).get(2);
                if(StringUtils.isBlank(evaluateFormula) || StringUtils.isBlank(evaluate)){
                    continue;
                }
                String[] parts = evaluate.split("->");
                //String level = levelMap.get(evaluate);
                String level = (String) data.get(i).get(6);
                if (StringUtils.isNotBlank(evaluateFormula) && StringUtils.isNotBlank(evaluate)){
                    //if (evaluateSet.contains(evaluate)){
                    //    continue;
                    //}
                    // evaluateSet.add(evaluate);
                     result = BooleanExpressionEvaluatorUtil.evaluate(evaluateFormula, booleanMap);

                    if (result) {
                        if(evaluateMap.get(evaluate) == null) {
                            evaluateMap.put(evaluate,buildEvResMap(level,"已达成") );
                        }else {
                            Map<String,String> resMap = evaluateMap.get(evaluate);
                            resMap.put(level,"已达成");
                            evaluateMap.put(evaluate,resMap);
                        }
                    } else {
                        if(evaluateMap.get(evaluate) == null) {
                            evaluateMap.put(evaluate,buildEvResMap(level,"未达成") );
                        }else {
                            Map<String,String> resMap = evaluateMap.get(evaluate);
                            resMap.put(level,"未达成");
                            evaluateMap.put(evaluate,resMap);
                        }
                    }
                    //if (result && !level.contains("+")) {
                    //    evaluateSet2.add(evaluate);
                    //}
                    //if (result && !level.contains("+")) {
                    //    evaluateMap.put(evaluate, "已达成");
                    //    evaluateSet2.add(evaluate);
                    //} else if (!result && !level.contains("+")){
                    //    evaluateMap.put(evaluate, "未达成");
                    //}
                }
                //计算每种等级评价项总数
                if (StringUtils.equals(level, "1")) {
                    oneSet.add(evaluate);
                    levelList.add("1");
                    colourMap.put(parts[parts.length -1],oneColour);
                    if (result) {
                        oneFinishSet.add(evaluate);
                    }
                } else if (StringUtils.equals(level, "1+")) {
                    onePlusSet.add(evaluate);
                    levelList.add("1+");
                    if (colourMap.get(parts[parts.length -1]) == null){
                        colourMap.put(parts[parts.length -1],onePlusColour);
                    }
                    if (result) {
                        onePlusFinishSet.add(evaluate);
                    }
                } else if (StringUtils.equals(level, "2")) {
                    twoSet.add(evaluate);
                    levelList.add("2");
                    colourMap.put(parts[parts.length -1],twoColour);
                    if (result) {
                        twoFinishSet.add(evaluate);
                    }
                } else if (StringUtils.equals(level, "2+")) {
                    twoPlusSet.add(evaluate);
                    levelList.add("2+");
                    if (colourMap.get(parts[parts.length -1]) == null){
                        colourMap.put(parts[parts.length -1],twoPlusColour);
                    }
                    if (result) {
                        twoPlusFinishSet.add(evaluate);
                    }
                } else if (StringUtils.equals(level, "3")) {
                    threeSet.add(evaluate);
                    levelList.add("3");
                    colourMap.put(parts[parts.length -1],threeColour);
                    if (result) {
                        threeFinishSet.add(evaluate);
                    }
                } else if (StringUtils.equals(level, "3+")) {
                    threePlusSet.add(evaluate);
                    levelList.add("3+");
                    if (colourMap.get(parts[parts.length -1]) == null){
                        colourMap.put(parts[parts.length -1],threePlusColour);
                    }
                    if (result) {
                        threePlusFinishSet.add(evaluate);
                    }
                } else if (StringUtils.equals(level, "4")) {
                    fourSet.add(evaluate);
                    levelList.add("4");
                    if (colourMap.get(parts[parts.length -1]) == null){
                        colourMap.put(parts[parts.length -1],fourPlusColour);
                    }
                    if (result) {
                        fourFinishSet.add(evaluate);
                    }
                } else if (StringUtils.equals(level, "4+")) {
                    fourPlusSet.add(evaluate);
                    levelList.add("4+");
                    colourMap.put(parts[parts.length -1],fourPlusColour);
                    if (result) {
                        fourPlusFinishSet.add(evaluate);
                    }
                }
            }

            //计算等级达成率 && 拼装表格数据
            if (!CollectionUtils.isEmpty(levelList)){
                //levelList排序
                List<String> sortedList = new ArrayList<>(levelList);
                Collections.sort(sortedList, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        // 指定顺序
                        return getOrder(o1) - getOrder(o2);
                    }
                    // 自定义顺序映射
                    private int getOrder(String level) {
                        switch (level) {
                            case "1": return 1;
                            case "1+": return 2;
                            case "2": return 3;
                            case "2+": return 4;
                            case "3": return 5;
                            case "3+": return 6;
                            case "4": return 7;
                            case "4+": return 8;
                            default: return 9;// 默认较大值放后面
                        }
                    }
                });
                //遍历集合
                for (String level : sortedList) {
                    if (StringUtils.equals(level, "1")){
                        double oneFinishRateValue = (double) oneFinishSet.size() / oneSet.size() ;
                        oneFinishRate = String.format("%.2f", oneFinishRateValue * 100) + "%";
                        levelTableBuilder.append("<tr>\n" + "    <td>" + level + "</td>\n" + "    <td>" + oneSet.size() + "</td>\n" + "    <td>" + oneFinishSet.size() + "</td>\n" + "    <td>" + oneFinishRate + "</td>\n" + "</tr>");
                        }else if (StringUtils.equals(level, "1+")){
                        double onePlusFinishRateValue = (double) onePlusFinishSet.size() / onePlusSet.size();
                        onePlusFinishRate = String.format("%.2f", onePlusFinishRateValue * 100) + "%";
                        levelTableBuilder.append("<tr>\n" + "    <td>" + level + "</td>\n" + "    <td>" + onePlusSet.size() + "</td>\n" + "    <td>" + onePlusFinishSet.size() + "</td>\n" + "    <td>" + onePlusFinishRate + "</td>\n" + "</tr>");
                        }else if (StringUtils.equals(level, "2")){
                        double twoFinishRateValue =  (double) twoFinishSet.size() / twoSet.size();
                        twoFinishRate = String.format("%.2f", twoFinishRateValue * 100) + "%";
                        levelTableBuilder.append("<tr>\n" + "    <td>" + level + "</td>\n" + "    <td>" + twoSet.size() + "</td>\n" + "    <td>" + twoFinishSet.size() + "</td>\n" + "    <td>" + twoFinishRate + "</td>\n" + "</tr>");
                        }else if (StringUtils.equals(level, "2+")){
                        double twoPlusFinishRateValue = (double) twoPlusFinishSet.size() / twoPlusSet.size();
                        twoPlusFinishRate = String.format("%.2f", twoPlusFinishRateValue * 100) + "%";
                        levelTableBuilder.append("<tr>\n" + "    <td>" + level + "</td>\n" + "    <td>" + twoPlusSet.size() + "</td>\n" + "    <td>" + twoPlusFinishSet.size() + "</td>\n" + "    <td>" + twoPlusFinishRate + "</td>\n" + "</tr>");
                        }else if (StringUtils.equals(level, "3")){
                        double threeFinishRateValue = (double) threeFinishSet.size() / threeSet.size();
                        threeFinishRate = String.format("%.2f", threeFinishRateValue * 100) + "%";
                        levelTableBuilder.append("<tr>\n" + "    <td>" + level + "</td>\n" + "    <td>" + threeSet.size() + "</td>\n" + "    <td>" + threeFinishSet.size() + "</td>\n" + "    <td>" + threeFinishRate + "</td>\n" + "</tr>");
                        }else if (StringUtils.equals(level, "3+")){
                        double threePlusFinishRateValue = (double) threePlusFinishSet.size() / threePlusSet.size();
                        threePlusFinishRate = String.format("%.2f", threePlusFinishRateValue * 100) + "%";
                        levelTableBuilder.append("<tr>\n" + "    <td>" + level + "</td>\n" + "    <td>" + threePlusSet.size() + "</td>\n" + "    <td>" + threePlusFinishSet.size() + "</td>\n" + "    <td>" + threePlusFinishRate + "</td>\n" + "</tr>");
                        }else if (StringUtils.equals(level, "4")){
                        double fourFinishRateValue = (double) fourFinishSet.size() / fourSet.size();
                        fourFinishRate = String.format("%.2f", fourFinishRateValue * 100) + "%";
                        levelTableBuilder.append("<tr>\n" + "    <td>" + level + "</td>\n" + "    <td>" + fourSet.size() + "</td>\n" + "    <td>" + fourFinishSet.size() + "</td>\n" + "    <td>" + fourFinishRate + "</td>\n" + "</tr>");
                        }else if (StringUtils.equals(level, "4+")){
                        double fourPlusFinishRateValue = (double) fourPlusFinishSet.size() / fourPlusSet.size();
                        fourPlusFinishRate = String.format("%.2f", fourPlusFinishRateValue * 100) + "%";
                        levelTableBuilder.append("<tr>\n" + "    <td>" + level + "</td>\n" + "    <td>" + fourPlusSet.size() + "</td>\n" + "    <td>" + fourPlusFinishSet.size() + "</td>\n" + "    <td>" + fourPlusFinishRate + "</td>\n" + "</tr>");
                        }
                    }
                   levelTable = String.valueOf(levelTableBuilder);
                }
            //达成率
            if (!evaluateMap.isEmpty()){
                AtomicInteger yes = new AtomicInteger();
                evaluateMap.forEach((k,v) -> {
                    if (v.size() >= 2) {
                        v.forEach((k1,v1)->{
                            if ("已达成".equals(v1)&&!k1.contains("+")) {
                                yes.getAndIncrement();
                            }
                        });
                    }else {
                        v.forEach((k1, v1) -> {
                            if ("已达成".equals(v1)) {
                                yes.getAndIncrement();
                            }
                        });
                    }
                });
                //for (Map.Entry<String, String> entry : evaluateMap.entrySet()) {
                //    if ("已达成".equals(entry.getValue())) {
                //        evaluateSet2.add(entry.getKey());
                //    }
                //}
                double evaluateAccuracyValue = (double) yes.get()/evaluateMap.size() * 100;
                evaluateAccuracy = String.format("%.2f", evaluateAccuracyValue) + "%";
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        ArrayList<Node> nodeList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(new JSONObject().put("topic", toolTypeNew).put("isroot", true).put("id", "root"));
        try {
            List<String> appraiseArrayList = new ArrayList<>(appraiseList);
            HashSet<String> nameList = new HashSet<>();
            String id = null;
            int number = 0;
            for (int i = 0; i < appraiseArrayList.size(); i++) {
                String appraise = appraiseArrayList.get(i);
                if (StringUtils.isBlank(appraise)){
                    continue;
                }
                String[] parts = appraise.split("->");
                for (int k = 0; k < parts.length; k++) {
                    String part = parts[k];
                    String finalId = String.valueOf(i) + String.valueOf(k);
                    if (nameList.contains(part)) {
                        number++;
                        if (number == parts.length) {
                            number = 0;
                        }
                        continue;
                    }
                    nameList.add(part);
                    if (k == 0) {
                        Node nodeOne = new Node(finalId, "root", part);
                        nodeList.add(nodeOne);
                        if (k == parts.length-1){
                            jsonArray.put(new JSONObject().put("topic", part).put("parentid", "root").put("id", finalId).put("background-color",colourMap.get(parts[k])));
                        }else {
                            jsonArray.put(new JSONObject().put("topic", part).put("parentid", "root").put("id", finalId).put("background-color",""));
                        }
                        if (k == parts.length-1){
                            if (evaluateMap.containsKey(appraise)){
                                Map<String,String> evaluate = evaluateMap.get(appraise);
                                AtomicReference<String> evaluateRes = new AtomicReference<>("");
                                if(evaluate.size()>=2){
                                    evaluate.forEach((a, b) -> {
                                        if (!a.contains("+")){
                                            evaluateRes.set(b);
                                        }
                                       }
                                    );
                                }else {
                                    evaluate.forEach((a,b)->evaluateRes.set(b));
                                }
                                if (StringUtils.equals(evaluateRes.get(), "已达成")){
                                    jsonArray.put(new JSONObject().put("topic", "已达成").put("parentid", finalId).put("id","sub"+finalId).put("background-color","#32CD32"));
                                }else {
                                    jsonArray.put(new JSONObject().put("topic", "未达成").put("parentid", finalId).put("id","sub"+finalId).put("background-color","#FF7F50"));
                                }
                            }
                        }
                    }else {
                        if (number != 0) {
                            String partRoot = parts[number - 1];
                            for (Node node : nodeList) {
                                if (StringUtils.equals(node.getTopic(), partRoot)) {
                                    id = node.getId();
                                }
                            }
                            Node nodeTwo = new Node(finalId, id, part);
                            nodeList.add(nodeTwo);
                            if(k == parts.length-1){
                                jsonArray.put(new JSONObject().put("topic", part).put("parentid", id).put("id", finalId).put("background-color",colourMap.get(parts[k])));
                            }else {
                                jsonArray.put(new JSONObject().put("topic", part).put("parentid", id).put("id", finalId).put("background-color",""));
                            }
                            number = 0;
                            if (k == parts.length-1){
                                if (evaluateMap.containsKey(appraise)){
                                    Map<String,String> evaluate = evaluateMap.get(appraise);
                                    AtomicReference<String> evaluateRes = new AtomicReference<>("");
                                    if(evaluate.size()>=2){
                                        evaluate.forEach((a, b) -> {
                                                    if (!a.contains("+")){
                                                        evaluateRes.set(b);
                                                    }
                                                }
                                        );
                                    }else {
                                        evaluate.forEach((a,b)->evaluateRes.set(b));
                                    }
                                    if (StringUtils.equals(evaluateRes.get(), "已达成")){
                                        jsonArray.put(new JSONObject().put("topic", "已达成").put("parentid", finalId).put("id","sub"+finalId).put("background-color","#32CD32"));
                                    }else {
                                        jsonArray.put(new JSONObject().put("topic", "未达成").put("parentid", finalId).put("id","sub"+finalId).put("background-color","#FF7F50"));
                                    }
                                }
                            }
                        }else {
                            Node nodeTwo = new Node(finalId, String.valueOf(i) + String.valueOf(k - 1), part);
                            nodeList.add(nodeTwo);
                            if (k == parts.length-1){
                                jsonArray.put(new JSONObject().put("topic", part).put("parentid", String.valueOf(i) + String.valueOf(k - 1)).put("id", finalId).put("background-color",colourMap.get(parts[k])));
                            }else {
                                jsonArray.put(new JSONObject().put("topic", part).put("parentid", String.valueOf(i) + String.valueOf(k - 1)).put("id", finalId).put("background-color",""));
                            }
                            if (k == parts.length-1){
                                if (evaluateMap.containsKey(appraise)){
                                    Map<String,String> evaluate = evaluateMap.get(appraise);
                                    AtomicReference<String> evaluateRes = new AtomicReference<>("");
                                    if(evaluate.size()>=2){
                                        evaluate.forEach((a, b) -> {
                                                    if (!a.contains("+")){
                                                        evaluateRes.set(b);
                                                    }
                                                }
                                        );
                                    }else {
                                        evaluate.forEach((a,b)->evaluateRes.set(b));
                                    }
                                    if (StringUtils.equals(evaluateRes.get(), "已达成")){
                                        jsonArray.put(new JSONObject().put("topic", "已达成").put("parentid", finalId).put("id","sub"+finalId).put("background-color","#32CD32"));
                                    }else {
                                        jsonArray.put(new JSONObject().put("topic", "未达成").put("parentid", finalId).put("id","sub"+finalId).put("background-color","#FF7F50"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Problem Tree view");
            e.printStackTrace();
        }

        Path xastToolFilePath = null; // Save value for use in a later step
        try {
            xastToolFilePath = new File(fileName.replace(".xlsx", ".html")).toPath();
        } catch (Exception e) {
            System.out.println("Problem copying XastTool files");
            e.printStackTrace();
        }

        try {
            ClassLoader CL = BenchmarkScore.class.getClassLoader();
            InputStream vulnTemplateStream = CL.getResourceAsStream("scorecard/XastTool.html");
            String html = IOUtils.toString(vulnTemplateStream, StandardCharsets.UTF_8);
            html = html.replace("${工具名}", toolName)
                        .replace("${工具类型}", toolTypeNew)
                        .replace("${case总数}", String.valueOf((int)cases))
                        .replace("${TP}", String.valueOf((int)TP))
                        .replace("${FP}", String.valueOf((int)FP))
                        .replace("${TN}", String.valueOf((int)TN))
                        .replace("${FN}", String.valueOf((int)FN))
                        .replace("${准确率}", accuracy)
                        .replace("${召回率}", recall)
                        .replace("${评价项达成率}", evaluateAccuracy)
                        .replace("${level_table}", levelTable)
                        .replace("${xmind_data}", jsonArray.toString());

            //Files.write(xastToolFilePath, html.getBytes());
            Files.write(xastToolFilePath, Collections.singleton(html), StandardCharsets.UTF_8);
            System.out.println("扫描工具在xAST评价体系下的评价结果已输出到: " + xastToolFilePath);
        } catch (IOException e) {
            System.out.println("Error updating results table in: " + xastToolFilePath.getFileName());
            e.printStackTrace();
        }
    }


    /**
     * Return map of each vuln category to the actual result counts for that category in the
     * supplied TestSuiteResults.
     *
     * @return A Map<String, TP_FN_TN_FP_Counts> of the vuln categories by name, to the scores for
     *     this tool.
     */

    private static TestSuiteResults readActualResultsNew(File fileToParse) throws Exception {
        ResultFile resultFile = new ResultFile(fileToParse);
        TestSuiteResults tr = null;

        Optional<Reader> reader =
                Reader.allReaders().stream().filter(r -> r.canRead(resultFile)).findAny();

        if (reader.isPresent()) {
            tr = reader.get().parse(resultFile);
        }
        return tr;
    }
    /**
     * Go through each expected result, and figure out if this tool actually passed or not. This
     * updates the expected results to reflect what passed/failed.
     *
     * <p>The vendor-specific category in TestSuiteResults actual is not used. The actual tests are
     * matched to the corresponding expected tests by CWE numberBenchmark-specific category in
     * TestResults expected, and the Benchmark-specific category in TestResults expected is used
     * instead.
     *
     * <p>TODO: Do not cause the side effect by modifying expected.
     *
     * @param expected - The expected results for this test suite.
     * @param rawToolResults - The actual results for this tool
     * @return The scored results for this tool.
     */
    private static TestSuiteResults analyzeNew(
            List<TestCaseResult> expected, TestSuiteResults rawToolResults) {
        TestSuiteResults expResult =
                new TestSuiteResults(
                        rawToolResults.getToolName(), false, rawToolResults.getToolType());
        if (expected == null || expected.size() == 0) {
            return expResult;
        }
        for (TestCaseResult exp : expected) {
            boolean match = false;
            for (TestCaseResult raw : rawToolResults.getTcrs()) {
                if (StringUtils.isNotEmpty(exp.getTestCaseName())
                        && StringUtils.equals(exp.getTestCaseName(), raw.getTestCaseName())) {
                    match = true;
                    break;
                }
                if (StringUtils.isNotEmpty(exp.getUrl())
                        && StringUtils.equals(exp.getUrl(), raw.getUrl())) {
                    match = true;
                    break;
                }
            }
            exp.setMatchResult(match);
        }
        expResult.setTcrs(expected);
        expResult.setToolVersion(rawToolResults.getToolVersion());
        return expResult;
    }
    /**
     * Check all actual results. If a real vulnerability matches, then exit. Otherwise keep going.
     *
     * @param exp The expected results
     * @param actList The list of actual results for this test case.
     * @return true if the expected result is found in the actual result (i.e., If True Positive,
     *     that results was found, If False Positive, that result was not found.)
     */
    private static boolean compare(TestCaseResult exp, List<TestCaseResult> actList, String tool) {
        // return true if there are no actual results and this was a false positive test
        if (actList == null || actList.isEmpty()) {
            return !exp.isTruePositive();
        }

        // otherwise check actual results
        for (TestCaseResult act : actList) {
            // Helpful in debugging
            // System.out.println( "  Evidence: " + act.getCWE() + " " + act.getEvidence() + "[" +
            // act.getConfidence() + "]");

            int actualCWE = act.getCWE();
            int expectedCWE = exp.getCWE();

            boolean match = actualCWE == expectedCWE;

            // Special case: many tools report CWE 89 (sqli) for Hibernate Injection (hqli) rather
            // than actual CWE of 564 So we accept either
            if (!match && (expectedCWE == 564)) {
                match = (actualCWE == 89);
            }

            // special hack since IBM/Veracode and CodeQL don't distinguish different kinds of weak
            // algorithm
            if (tool.startsWith("AppScan")
                    || tool.startsWith("Vera")
                    || tool.startsWith("CodeQL")) {
                if (expectedCWE == 328 && actualCWE == 327) {
                    match = true;
                }
            }

            // return true if we find an exact match for a True Positive test
            if (match) {
                return exp.isTruePositive();
            }
        }
        // if we couldn't find a match, then return true if it's a False Positive test
        return !exp.isTruePositive();
    }

    private static boolean compareNew(
            TestCaseResult exp, List<TestCaseResult> actList, ToolType tool) {
        // return true if there are no actual results and this was a false positive test
        if (actList == null || actList.isEmpty()) {
            return !exp.isTruePositive();
        }

        // otherwise check actual results
        for (TestCaseResult act : actList) {
            if (StringUtils.equals(exp.getTestCaseName(), act.getTestCaseName())) {
                return exp.isTruePositive();
            }
            if (StringUtils.equals(exp.getUrl(), act.getUrl())) {
                return exp.isTruePositive();
            }
        }
        // if we couldn't find a match, then return true if it's a False Positive test
        return !exp.isTruePositive();
    }
    // Create a TestResults object that contains the expected results for this version
    // of the test suite.
    private static TestSuiteResults readExpectedResults(File file) {
        TestSuiteResults tr = new TestSuiteResults("Expected", true, null);

        try (final BufferedReader fr = new BufferedReader(new FileReader(file))) {
            // Read the 1st line. Parse out the test suite name and version #, which looks like:
            // # test name, category, real vulnerability, cwe, TESTSUITENAME version: x.y,
            // YYYY-MM-DD

            String line = fr.readLine();
            final String TESTSUITE_VERSION_PREFIX = " version: ";
            if (line != null) {
                String[] firstLineElements = line.split(", ");
                int startOfVersionStringLocation =
                        firstLineElements[4].indexOf(TESTSUITE_VERSION_PREFIX);
                if (startOfVersionStringLocation != -1) {
                    final String TESTSUITENAME =
                            firstLineElements[4].substring(0, startOfVersionStringLocation);
                    tr.setTestSuiteName(TESTSUITENAME);
                    BenchmarkScore.TESTSUITE = TESTSUITENAME; // Set classwide variable
                    BenchmarkScore.TESTCASENAME = // Set classwide variable;
                            TESTSUITENAME + TEST;
                    startOfVersionStringLocation += TESTSUITE_VERSION_PREFIX.length();
                } else {
                    String versionNumError =
                            "Couldn't find "
                                    + TESTSUITE_VERSION_PREFIX
                                    + " on first line of expected results file";
                    System.out.println(versionNumError);
                    throw new IOException(versionNumError);
                }
                // Trim off everything except the version #
                line = firstLineElements[4].substring(startOfVersionStringLocation);
                tr.setTestSuiteVersion(line);
            }

            boolean reading = true;
            while (reading) {
                line = fr.readLine();
                reading = line != null;
                if (reading) {
                    // Normally, each line contains: test name, category, real vulnerability, cwe #

                    // String[] parts = line.split(",");
                    // regex from
                    // http://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                    // This regex needed because some 'full details' entries contain comma's inside
                    // quoted strings
                    String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    if (parts[0] != null && parts[0].startsWith(TESTCASENAME)) {
                        TestCaseResult tcr = new TestCaseResult();
                        tcr.setTestCaseName(parts[0]);
                        tcr.setCategory(parts[1]);
                        tcr.setTruePositive(Boolean.parseBoolean(parts[2]));
                        tcr.setCWE(Integer.parseInt(parts[3]));

                        tcr.setNumber(Reader.testNumber(parts[0]));

                        // Handle situation where expected results has full details
                        // Sometimes, it also has: source, data flow, data flow filename, sink

                        if (parts.length > 4) {
                            tcr.setSource(parts[4]);
                            tcr.setDataFlow(parts[5]);
                            // tcr.setDataFlowFile(parts[6]);
                            tcr.setSink(parts[6]);
                        }

                        tr.put(tcr);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Can't find expected results file: " + file);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("ERROR: Reading contents of expected results file: " + file);
            e.printStackTrace();
            System.exit(-1);
        }
        return tr;
    }

    //private static List<String> benchmarkWithLang= Lists.newArrayList("sast-java","dast-java","iast-java","sast-python");
    //private static List<String> benchmarkWithLang = Lists.newArrayList("sast-java","sast-python3","sast-python2","sast-js","sast-go","iast-java","dast-java");
    //
    //private static List<String> toolPre = Lists.newArrayList("sast","iast","dast");
    private static boolean containsToolPath(String scanPath,Map<String, List<TestCaseResult>> results){
        for (String s : results.keySet()) {
            if (scanPath.contains(s)){
                return true;
            }
        }
        return false;
    }
    private static Map<String, List<TestCaseResult>> readExpectedResultsNew(String directoryPath,String lang) {
        Map<String, List<TestCaseResult>> result = new HashMap<>();

        //for (String benchmark: benchmarkWithLang) {
        //    result.put(benchmark,new ArrayList<>());
        //}
        for (ToolType value : ToolType.values()) {
            result.put(value.name().toLowerCase()+"-"+lang,new ArrayList<>());
        }
        List<String> benchmarkWithLang=result.keySet().stream().collect(Collectors.toList());
        //List<String> datas = new ArrayList<>();
        try (Stream<Path> filePathStream =
                     Files.find(
                             Paths.get(directoryPath),
                             Integer.MAX_VALUE,
                             (path, attr) ->
                                     attr.isRegularFile()
                                             //&& path.toString().contains("cases"),
                                             && path.toString().contains("case")
                                             && containsToolPath(path.toString(),result),
                             FileVisitOption.FOLLOW_LINKS)) {
            // 收集所有文件路径到一个 List 中
            //List<Path> filePaths = filePathStream.collect(Collectors.toList());
            filePathStream.forEach(
                    f -> {
                        try {
                            System.out.println("\nProcessing file: " + f);
                            for (String benchmark : benchmarkWithLang) {
                                if(f.toString().contains(benchmark)){
                                    putInfoToTr(result.get(benchmark), f);
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            System.err.println("Error processing file: " + f);
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try (Stream<Path> filePathStream =
                     Files.find(
                             Paths.get(directoryPath),
                             Integer.MAX_VALUE,
                             (path, attr) ->
                                     attr.isRegularFile()
                                             && path.toString().endsWith("config.json")
                                             &&containsToolPath(path.toString(),result),
                             FileVisitOption.FOLLOW_LINKS)) {
            // 收集所有文件路径到一个 List 中
            //List<Path> filePaths = filePathStream.collect(Collectors.toList());
            filePathStream.forEach(
                    f -> {
                        try {
                            System.out.println("\nProcessing file: " + f);
                            for (String benchmark : benchmarkWithLang) {
                                if(f.toString().contains(benchmark)){
                                    processJsonFile(result.get(benchmark), f);
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.err.println("Error processing file: " + f);
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return result;
    }

    private static void processJsonFileOld(List<TestCaseResult> testCaseResults, Path filePath) {
        // 实现对 JSON 文件内容的解析逻辑
        // 这个方法应读取 JSON 文件，解析其内容，并将解析后的结果添加到 result 映射中
        try{
            String jsonString = Files.readString(filePath);
            if (StringUtils.isBlank(jsonString)){
                System.out.println("jsonString is null,path :" + filePath);
                return;
            }
            //判断是否为json格式
            if (!isValidJson(jsonString)){
                System.out.println("jsonString is not json format,path :" + filePath);
                return;
            }
            // 将字符串转换为 JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);
            // 获取文件名的父目录
            Path parentDirectory = filePath.getParent();
            // 获取最后一个子目录名
            String key = parentDirectory.getFileName().toString();
            Object o = jsonObject.get(key);
            if (o instanceof JSONArray){
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                for (Object o1 : jsonArray) {
                    if (o1 instanceof JSONObject){
                        // 创建一个 StringBuilder 来存储所有 compose 字段
                        StringBuilder composeBuilder = new StringBuilder();
                        JSONObject jsonObject1 = (JSONObject) o1;
                        String evaluationItem = String.valueOf(jsonObject1.get("evaluation item"));
                        JSONArray sceneList = jsonObject1.getJSONArray("scene_list");
                        for (Object scene : sceneList) {
                            if (scene instanceof JSONObject){
                                JSONObject sceneJsonObject = (JSONObject) scene;
                                String compose = String.valueOf(sceneJsonObject.get("compose"));
                                // 将 compose 添加到 StringBuilder 中
                                if (composeBuilder.length() > 0) {
                                    composeBuilder.append(","); // 如果不是第一个元素，先添加逗号
                                }
                                composeBuilder.append(compose); // 添加当前的 compose
                            }
                        }
                        String level = String.valueOf(jsonObject1.get("level"));
                        for (TestCaseResult testCaseResult : testCaseResults) {
                            if (StringUtils.equals(testCaseResult.getAssesionProject(),evaluationItem)){
                                testCaseResult.setCompose(String.valueOf(composeBuilder));
                                testCaseResult.setLevel(level);
                            }
                        }
                        //将评价项和等级关系存入map
                        levelMap.put(evaluationItem,level);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void processJsonFile(List<TestCaseResult> testCaseResults, Path filePath) {
        try {
            // 记录处理开始的日志
            //System.out.println("Starting to process file: " + filePath);

            String jsonString = Files.readString(filePath);
            if (StringUtils.isBlank(jsonString)) {
                //System.out.println("jsonString is null or blank, path: " + filePath);
                return;
            }
            // 确认JSON字符串读取正确性
            //System.out.println("Read jsonString from file: " + filePath);

            if (!isValidJson(jsonString)) {
                //System.out.println("jsonString is not in json format, path: " + filePath);
                return;
            }

            JSONObject jsonObject = new JSONObject(jsonString);
            // 获取并记录父目录信息
            Path parentDirectory = filePath.getParent();

            String key = parentDirectory.getFileName().toString();

            Object o = jsonObject.get(key);

            if (o instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) o;
                for (Object item : jsonArray) {
                    if (item instanceof JSONObject) {
                        JSONObject itemObj = (JSONObject) item;
                        String evaluationItem = String.valueOf(itemObj.get("evaluation_item"));


                        JSONArray sceneLevels = itemObj.getJSONArray("scene_levels");
                        StringBuilder composeBuilder = new StringBuilder();
                        StringBuilder composeBuilderAdd = new StringBuilder();
                        for (Object sceneLevelObj : sceneLevels) {
                            if (sceneLevelObj instanceof JSONObject) {
                                JSONObject sceneLevel = (JSONObject) sceneLevelObj;
                                String level = String.valueOf(sceneLevel.get("level"));
                                //System.out.println("Processing level: " + level);


                                JSONArray sceneList = sceneLevel.getJSONArray("scene_list");
                                for (Object sceneObj : sceneList) {

                                    if (sceneObj instanceof JSONObject) {
                                        JSONObject scene = (JSONObject) sceneObj;
                                        String compose = String.valueOf(scene.get("compose"));
                                        //if (!compose.contains("/")){
                                        //    String processed = compose.replace("&&", " ").replace("!", " ")
                                        //            .replace("||"," ").replace("("," ").replace(")"," ");
                                        //    List<String> fileNames = new ArrayList<>(Arrays.asList(processed.split("\\s+")));
                                        //    for (String fileName : fileNames) {
                                        //        levelMap.put(fileName,level);
                                        //    }
                                        //}else {
                                            List<String> fileNames = extractPyFiles(compose);
                                            for (String fileName : fileNames) {
                                                levelMap.put(fileName,level);
                                            }
                                        //}


                                        //System.out.println("Processing compose: " + compose);


                                        if (!level.contains("+")) {
                                            if (composeBuilder.length() > 0) {
                                                composeBuilder.append(","); // 如果不是第一个元素，先添加逗号
                                            }
                                            composeBuilder.append(compose); // 添加当前的 compose

                                        }
                                        if (level.contains("+")) {
                                            if (composeBuilderAdd.length() > 0) {
                                                composeBuilderAdd.append(","); // 如果不是第一个元素，先添加逗号
                                            }
                                            composeBuilderAdd.append(compose); // 添加当前的 compose
                                        }
                                    }
                                }
                            }
                        }
                        composeMap.put(evaluationItem, String.valueOf(composeBuilder));
                        composeMap.put(evaluationItem+"Add", String.valueOf(composeBuilderAdd));


                    }
                }

                for (TestCaseResult testCaseResult : testCaseResults) {
                    testCaseResult.setLevel(levelMap.get(testCaseResult.getTestCaseName()));
                    if (StringUtils.isNotEmpty(composeMap.get(testCaseResult.getAssesionProject()))){
                        //try {
                        //    levelMap.get(testCaseResult.getTestCaseName()).contains("+");
                        //}catch (Exception e){
                        //    System.out.println(testCaseResult.getAssesionProject()+"@@@"+testCaseResult.getTestCaseName());
                        //}

                        if (!levelMap.get(testCaseResult.getTestCaseName()).contains("+")){
                            testCaseResult.setCompose(composeMap.get(testCaseResult.getAssesionProject()));
                        }else {
                            testCaseResult.setCompose(composeMap.get(testCaseResult.getAssesionProject()+"Add"));
                        }
                    }else{
                        testCaseResult.setCompose(composeMap.get(testCaseResult.getAssesionProject()+"Add"));
                    }

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static List<String> extractPyFiles(String input) {
        List<String> validExtensions = Arrays.asList(".js", ".go", ".java", ".py");
        List<String> fileNames = new ArrayList<>();

        // 按逻辑符号分割路径项（如 ||、&&、括号、!）
        String[] parts = input.split("\\s*(\\|\\||&&|!|\\(|\\))\\s*");

        for (String part : parts) {
            String cleanedPart = part.trim();
            if (cleanedPart.isEmpty()) continue;

            // 提取文件名（保留路径中的最后部分）
            int lastSlashIndex = cleanedPart.lastIndexOf('/');
            String fileName = (lastSlashIndex != -1)
                    ? cleanedPart.substring(lastSlashIndex + 1)
                    : cleanedPart;

            // 检查扩展名是否合法
            if (fileName.contains(".") && validExtensions.contains(
                    fileName.substring(fileName.lastIndexOf('.')).toLowerCase())) {
                fileNames.add(fileName);
            }
        }

        return fileNames;
    }


    public static boolean isValidJson(String jsonString) {
        try {
            new JSONObject(jsonString); // 尝试解析为 JSONObject
        } catch (Exception e) {
            return false;  // 解析失败，则返回 false
        }
        return true;  // 至少一个解析成功，返回 true
    }

    public static void putInfoToTr(List<TestCaseResult> testCaseResults, Path filePath)
            throws IOException {
        boolean insideSection = false;
        String[] datas = new String[5];
        List<String> lines = Files.readAllLines(filePath);
        datas[0] = filePath.getFileName().toString();
        for (String line : lines) {
            if (line.trim().equalsIgnoreCase(START_MARKER) || line.contains(START_MARKER)) {
                insideSection = true;
            } else if (line.trim().equalsIgnoreCase(END_MARKER) || line.contains(END_MARKER)) {
                break;
            } else if (insideSection && line.contains("=")) {
                String[] kv = line.split("=",2);
                if (kv.length == 2) {
                    if (kv[0].contains("evaluation item")) {
                        datas[2] = kv[1].trim();
                    } else if (kv[0].contains("bind_url")) {
                        String bindUrl = kv[1].trim();
                        datas[1] = bindUrl.startsWith("/") ? bindUrl : "/" + bindUrl;
                    } else if (kv[0].contains("real case")) {
                        datas[4] = kv[1].trim();
                    }
                }
            }
        }
        if (StringUtils.isNotEmpty(datas[1])) {
            TestCaseResult tcr = new TestCaseResult();
            tcr.setTestCaseName(datas[0]);
            tcr.setUrl(datas[1]);
            tcr.setAssesionProject(datas[2]);
            tcr.setIdentifiedVul(datas[4]);
            testCaseResults.add(tcr);
        }
    }
    /**
     * This produces the .xlsx of all the results for this tool. It's basically the expected results
     * file with a couple of extra columns in it to say what the actual result for this tool was per
     * test case and whether that result was a pass or fail.
     *
     * @param actuals The actual TestResults to produce the actual results file for.
     * @return The name of the results file produced
     */
    private static String produceResultsFileNew(TestSuiteResults actuals, File scoreCardDir, String  toolType,String lang) {
        String resultsFileName =
                scoreCardDir.getAbsolutePath()
                        + File.separator
                        + "xAST_v_1.0.0_"
                        + actuals.getToolNameAndVersion().replace(' ', '_')
                        + "_"
                        + toolType.toLowerCase(Locale.ROOT)
                        + "_"
                        + lang
                        + ".xlsx";
        File resultsFile = new File(resultsFileName);

        try {

            ExcelWriterSheetBuilder xlxWriter = EasyExcel.write(resultsFileName, ScoreCardFormart.class).sheet("sheet1");
            xlxWriter.head(ScoreCardHeader.getHeader());
            ArrayList<ScoreCardFormart> scoreCards = new ArrayList<ScoreCardFormart>();

            // Write actual results body
            for (TestCaseResult actualResult : actuals.getTcrs()) {
                // Write meta data to file here.

                ScoreCardFormart scoreCardFormart = new ScoreCardFormart();
                scoreCardFormart.setFileName(actualResult.getTestCaseName());
                scoreCardFormart.setUrl(actualResult.getUrl());
                scoreCardFormart.setAssession(actualResult.getAssesionProject());
                scoreCardFormart.setCompose(actualResult.getCompose());
                scoreCardFormart.setHasVul(actualResult.getIdentifiedVul());
                scoreCardFormart.setFoundVul(String.valueOf(actualResult.isMatchResult()));
                scoreCardFormart.setLevel(actualResult.getLevel());
                scoreCards.add(scoreCardFormart);
            }
            xlxWriter.doWrite(scoreCards);


            System.out.println("Actual results file generated: " + resultsFile.getAbsolutePath());

            return resultsFile.getName();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null; // Should have returned results file name earlier if successful
    }

    // A utility method for providing a more descriptive test suite name than the base, single word,
    // test suite name.
    public static String fullTestSuiteName(String suite) {
        return ("Benchmark".equals(suite) ? "OWASP Benchmark" : suite);
    }
}
