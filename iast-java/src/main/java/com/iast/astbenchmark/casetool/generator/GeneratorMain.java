package com.iast.astbenchmark.casetool.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.iast.astbenchmark.casetool.parser.CaseJavaSourceCodeFileParser;
import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;
import com.iast.astbenchmark.casetool.parser.domain.ParseTask;
import com.iast.astbenchmark.common.XastIastException;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author CC11001100
 */
public class GeneratorMain {

    /**
     * @return
     */
    private static String getCasePath() {
        String projectRootPath = System.getProperty("user.dir");
        String caseRelativePath = "iast-java/src/main/java/com/iast/astbenchmark/cases";
        return projectRootPath + "/" + caseRelativePath;
    }

    /**
     * @param casePath
     * @return
     */
    private static List<ParseTask> traverseCollectParseTask(String casePath) {
        File casePathFile = new File(casePath);
        List<ParseTask> taskList = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(casePathFile);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null && files.length != 0) {
                    for (File listFile : files) {
                        queue.offer(listFile);
                    }
                }
            } else {
                String absolutePath = file.getAbsolutePath();
                String suffix = absolutePath.replace(casePathFile.getAbsolutePath(), "");
                ParseTask task = new ParseTask();

                String caseFullClassName = "com.iast.astbenchmark.cases" + suffix.replace(".java", "").replace("/", ".");
                task.setJavaFullClassName(caseFullClassName);

                task.setJavaSourceFilePath(absolutePath);
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * 判断是否是case文件
     *
     * @param result
     * @return
     */
    private static boolean isCaseFile(CaseJavaFileParseResult result) {
        return result.getHasVul() != null;
    }

    /**
     * @param taskList
     * @return
     */
    public static List<CaseJavaFileParseResult> executeParseTask(List<ParseTask> taskList) {
        List<CaseJavaFileParseResult> resultList = new ArrayList<>();
        CaseJavaSourceCodeFileParser parser = new CaseJavaSourceCodeFileParser();
        for (ParseTask task : taskList) {
            try {
                CaseJavaFileParseResult r = parser.parse(task);
                if (isCaseFile(r)) {
                    resultList.add(r);
                } else {
                    System.out.println(r.getJavaSourceFilePath() + " is not case file, ignore.");
                }
            } catch (XastIastException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                // TODO 错误统计
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * @param result
     * @param javaSourceCode
     */
    private static void replace(CaseJavaFileParseResult result, String javaSourceCode) {
        FileUtil.writeString(javaSourceCode, new File(result.getJavaSourceFilePath()), StandardCharsets.UTF_8);
    }

    /**
     * 对解析好的java源代码文件进行分组计算
     *
     * @param list
     * @return
     */
    private static Map<String, List<CaseJavaFileParseResult>> group(List<CaseJavaFileParseResult> list) {
        Map<String, List<CaseJavaFileParseResult>> groupMap = new HashMap<>();
        for (CaseJavaFileParseResult result : list) {
            String groupId = computeGroupId(result);
            List<CaseJavaFileParseResult> groupList = groupMap.get(groupId);
            if (groupList == null) {
                groupList = new ArrayList<>();
                groupMap.put(groupId, groupList);
            }
            groupList.add(result);
        }
        return groupMap;
    }

    /**
     * 计算分组信息
     *
     * @param result
     * @return
     */
    private static String computeGroupId(CaseJavaFileParseResult result) {
        String javaSourceFilePath = result.getJavaSourceFilePath();
        // AccuracyTrackTaintString_CopyOfRange_001_T.java
        String suffix = "_001_T.java";
        if (javaSourceFilePath.length() > suffix.length()) {
            javaSourceFilePath = javaSourceFilePath.substring(0, javaSourceFilePath.length() - suffix.length());
        }
        return DigestUtil.md5Hex(javaSourceFilePath);
    }

    public static void main(String[] args) throws XastGeneratorException {

        String casePath = getCasePath();
        List<ParseTask> taskList = traverseCollectParseTask(casePath);
        System.out.println(taskList);

        List<CaseJavaFileParseResult> results = executeParseTask(taskList);
//        System.out.println(results);

        // 计算组件之间的分组关系
        Map<String, List<CaseJavaFileParseResult>> groupMap = group(results);
        // 对应用进行分组
        Map<CaseJavaFileParseResult, List<CaseJavaFileParseResult>> resultToGroupMap = new HashMap<>();
        for (Map.Entry<String, List<CaseJavaFileParseResult>> groupEntry : groupMap.entrySet()) {
            for (CaseJavaFileParseResult result : groupEntry.getValue()) {
                resultToGroupMap.put(result, groupEntry.getValue());
            }
        }

        XastCommentGenerator generator = new XastCommentGenerator();
        for (CaseJavaFileParseResult result : results) {
            GeneratorTask task = new GeneratorTask();
            task.setCaseJavaFileParseResult(result);
            task.setGroupCaseList(resultToGroupMap.get(result));
            String newJavaSourceCode = generator.generate(task);
            System.out.println(newJavaSourceCode);
            replace(result, newJavaSourceCode);
        }

    }

}
