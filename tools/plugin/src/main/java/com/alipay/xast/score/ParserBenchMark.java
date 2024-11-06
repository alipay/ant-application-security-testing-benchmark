/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.xast.score;

import com.alipay.xast.score.TestSuiteResults.ToolType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/**
 * @author wb-chengzhiyong
 * @version T.java, v 0.1 2024年05月17日 下午2:08 wb-chengzhiyong
 */
public class ParserBenchMark {

    private static final String START_MARKER = "assession information start";
    private static final String END_MARKER = "assession information end";

    public static List<String> parseSessionInformation(Path filePath) throws IOException {
        boolean insideSection = false;
        List<String> content = new ArrayList<>();
        StringJoiner stringJoiner = new StringJoiner(",");
        String[] datas = new String[5];
        List<String> lines = Files.readAllLines(filePath);
        datas[0] = filePath.getFileName().toString();
        stringJoiner.add(filePath.getFileName().toString());
        for (String line : lines) {
            if (line.trim().equalsIgnoreCase(START_MARKER) || line.contains(START_MARKER)) {
                insideSection = true;
            } else if (line.trim().equalsIgnoreCase(END_MARKER) || line.contains(END_MARKER)) {
                break;
            } else if (insideSection && line.contains("=")) {
                String[] kv = line.split("=");
                if (kv.length == 2) {
                    if (kv[0].contains("assession project")) {
                        datas[1] = kv[1].trim();
                    } else if (kv[0].contains("compose")) {
                        datas[2] = kv[1].trim();
                    } else if (kv[0].contains("bind_url")) {
                        datas[3] = kv[1].trim();
                    }
                }
                content.add(line.trim());
            }
        }
        System.out.println(String.join(",", datas));
        return content;
    }

    public static void parseSessionInformationToStr(List<String> resultDatas, Path filePath)
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
                String[] kv = line.split("=");
                if (kv.length == 2) {
                    if (kv[0].contains("assession project")) {
                        datas[2] = kv[1].trim();
                    } else if (kv[0].contains("compose")) {
                        datas[3] = kv[1].trim();
                    } else if (kv[0].contains("bind_url")) {
                        String bindUrl = kv[1].trim();
                        datas[1] = bindUrl.startsWith("/") ? bindUrl : "/" + bindUrl;
                    } else if (kv[0].contains("real vulnerability")) {
                        datas[4] = kv[1].trim();
                    }
                }
            }
        }
        // System.out.println(String.join(",",datas));
        if (StringUtils.isNotEmpty(datas[1])) {
            resultDatas.add(String.join(",", datas));
        }
    }

    public static Map<String, List<String>> processDirectory(String directoryPath)
            throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        result.put("dast", new ArrayList<String>());
        result.put("sast", new ArrayList<String>());
        result.put("iast", new ArrayList<String>());
        List<String> datas = new ArrayList<>();
        try (Stream<Path> filePathStream =
                Files.find(
                        Paths.get(directoryPath),
                        Integer.MAX_VALUE,
                        (path, attr) ->
                                attr.isRegularFile()
                                        && path.toString().contains("cases")
                                        && (path.toString().contains("dast-java")
                                                || path.toString().contains("sast-java")),
                        FileVisitOption.FOLLOW_LINKS)) {
            filePathStream.forEach(
                    file -> {
                        try {
                            System.out.println("\nProcessing file: " + file);
                            if (file.toString().contains("dast-java")) {
                                parseSessionInformationToStr(result.get("dast"), file);
                                parseSessionInformationToStr(datas, file);
                            } else if (file.toString().contains("sast-java")) {
                                parseSessionInformationToStr(result.get("sast"), file);
                                parseSessionInformationToStr(datas, file);
                            } else if (file.toString().contains("iast-java")) {
                                parseSessionInformationToStr(result.get("iast"), file);
                                parseSessionInformationToStr(datas, file);
                            }
                        } catch (IOException e) {
                            System.err.println("Error processing file: " + file);
                            e.printStackTrace();
                        }
                    });
        }
        // Calendar c = Calendar.getInstance();
        // String s = String.format("%1$tY-%1$tm-%1$te", c);
        String header = "文件名,url, 评价项, 评价项达成计算公式, 存在漏洞, 扫描识别为漏洞";
        String csvName =
                produceResultsFile(
                        header,
                        datas,
                        new File(
                                "/home/admin/wb-chengzhiyong/zhouzhouxiangyu.zx/Ant-xAST-Utils/results"));
        System.out.println(new JSONObject(result));
        return result;
    }

    public static String produceResultsFile(String header, List<String> datas, File scoreCardDir) {
        String resultsFileName = scoreCardDir.getAbsolutePath() + File.separator + "test" + ".csv";
        File resultsFile = new File(resultsFileName);
        try (FileOutputStream fos = new FileOutputStream(resultsFile, false);
                PrintStream ps = new PrintStream(fos); ) {
            // Write actual results header
            ps.println(header);
            for (String data : datas) {
                ps.println(data);
            }
            System.out.println("Actual results file generated: " + resultsFile.getAbsolutePath());
            return resultsFile.getName();

        } catch (FileNotFoundException e) {
            System.out.println(
                    "ERROR: Can't create actual results file: " + resultsFile.getAbsolutePath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null; // Should have returned results file name earlier if successful
    }

    public static void main(String[] args) {
        // String folderPath =
        // "/home/admin/wb-chengzhiyong/ant-application-security-testing-benchmark/sast-java/src/main/java/com/sast/astbenchmark/cases/accuracy/fieldSensitive"; // Replace with the target directory path
        String folderPath =
                "/home/admin/wb-chengzhiyong/ant-application-security-testing-benchmark";
        try {
            processDirectory(folderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(TestSuiteResults.ToolType.SAST.equals(ToolType.IAST));
        System.out.println(TestSuiteResults.ToolType.SAST.equals(TestSuiteResults.ToolType.SAST));
    }
}
