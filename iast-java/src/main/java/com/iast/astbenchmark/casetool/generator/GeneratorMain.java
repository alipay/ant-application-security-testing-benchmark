package com.iast.astbenchmark.casetool.generator;

import com.iast.astbenchmark.casetool.parser.CaseJavaSourceCodeFileParser;
import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;
import com.iast.astbenchmark.casetool.parser.domain.ParseTask;
import com.iast.astbenchmark.common.XastIastException;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public static void main(String[] args) throws XastGeneratorException {

        String casePath = getCasePath();
        List<ParseTask> taskList = traverseCollectParseTask(casePath);
        System.out.println(taskList);

        List<CaseJavaFileParseResult> results = executeParseTask(taskList);
//        System.out.println(results);

        XastCommentGenerator generator = new XastCommentGenerator();
        for (CaseJavaFileParseResult result : results) {
            GeneratorTask task = new GeneratorTask();
            task.setCaseJavaFileParseResult(result);
            String newJavaSourceCode = generator.generate(task);
            System.out.println(newJavaSourceCode);
        }

    }

}
