package com.alipay.xast.score.parsers;

import com.alipay.xast.score.ResultFile;
import com.alipay.xast.score.TestCaseResult;
import com.alipay.xast.score.TestSuiteResults;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class YASAReader extends Reader {

  @Override
  public boolean canRead(ResultFile resultFile) {

    if (resultFile.file().isDirectory()) {
      List<ResultFile> resultFiles = handleDir(resultFile);
      for (ResultFile item : resultFiles) {
        if (handleCanRead(item)) {
          return true;
        }
      }
      return false;
    } else {
      return handleCanRead(resultFile);
    }
  }

  // 处理可读
  private boolean handleCanRead(ResultFile resultFile) {
    // 只匹配 .sarif 后缀的文件
    if (resultFile.filename().endsWith(".sarif")) {
      JSONArray yasaResult = getYASAResult(resultFile);
      return !yasaResult.isEmpty();
    }

    return false;
  }

  @Override
  public TestSuiteResults parse(ResultFile resultFile) throws Exception {
    if (resultFile.file().isDirectory()) {
      List<ResultFile> resultFiles = handleDir(resultFile);
      return dirParse(resultFiles);
    } else {
      return fileParse(resultFile);
    }
  }

  // 单文件解析器
  private TestSuiteResults fileParse(ResultFile resultFile) {
    TestSuiteResults results = new TestSuiteResults("yasa", false, TestSuiteResults.ToolType.SAST);

    results.setTime(resultFile.file());

    JSONObject json = resultFile.json();

    results.setToolVersion(json.getString("version"));

    // 获取 yasa 结果集合
    JSONArray yasaResultList = getYASAResult(resultFile);

    List<TestCaseResult> testCaseResults = handleYasaResult(yasaResultList);

    for (TestCaseResult testCase : testCaseResults) {
      results.getTcrs().add(testCase);
    }

    return results;
  }

  // 文件夹解析器
  private TestSuiteResults dirParse(List<ResultFile> resultFiles) throws Exception {
    TestSuiteResults results = new TestSuiteResults("yasa", false, TestSuiteResults.ToolType.SAST);

    for (ResultFile resultFile : resultFiles) {
      if (resultFile.filename().endsWith(".sarif")) {
        // 取第一个文件的时间和版本号
        if (results.getTime() == null) {
          results.setTime(resultFile.file());
        }
        JSONObject json = resultFile.json();

        if (results.getToolVersion() == null) {
          results.setToolVersion(json.getString("version"));
        }

        JSONArray yasaResultList = getYASAResult(resultFile);
        List<TestCaseResult> testCaseResults = handleYasaResult(yasaResultList);
        for (TestCaseResult testCase : testCaseResults) {
          results.getTcrs().add(testCase);
        }
      }
    }
    return results;
  }

  // 处理文件夹构造文件列表
  private List<ResultFile> handleDir(ResultFile resultFile) {
    File file = resultFile.file();
    // 收集所有文件路径
    List<ResultFile> resultFiles = new ArrayList<>();
    try {
      Files.walk(file.toPath())
              .filter(Files::isRegularFile)
              .forEach(path -> {
                try {
                  resultFiles.add(new ResultFile(path.toFile()));
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
              });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return resultFiles;
  }

  // 处理 yasa 结果返回测试用例结果集合
  private List<TestCaseResult> handleYasaResult(JSONArray yasaResultList) {
    List<TestCaseResult> results = new ArrayList<>();
    if (!yasaResultList.isEmpty()) {
      for (int i = 0; i < yasaResultList.length(); i++) {
        JSONArray yasaToolResult = yasaResultList.getJSONObject(i).getJSONArray("results");
        for (int j = 0; j < yasaToolResult.length(); j++) {
          String path = yasaToolResult.getJSONObject(j).getJSONObject("entrypoint").getString("filePath");
          String testCaseName = extractTestCaseName(path);
          TestCaseResult testCase = new TestCaseResult();
          testCase.setTestCaseName(testCaseName);
          results.add(testCase);
        }
      }
    }

    return results;
  }

  // 获取 yasa 结果
  private JSONArray getYASAResult(ResultFile resultFile) {
    JSONObject json = resultFile.json();
    JSONArray runs = json.getJSONArray("runs");

    JSONArray yasaResults = new JSONArray();
    for (int i = 0; i < runs.length(); i++) {
      JSONObject run = runs.getJSONObject(i);
      if (isYASA(run)) {
        yasaResults.put(run);
      }
    }
    return yasaResults;
  }

  // 获取检测工具名称
  private Boolean isYASA(JSONObject run) {
    return run.getJSONObject("tool").getJSONObject("driver").getString("name").equals("yasa");
  }

  private String extractTestCaseName(String filePath) {
    // 从路径中提取测试用例名称
    return filePath.substring(filePath.lastIndexOf('/') + 1);
  }

  private int figureCWE(String rule) {
    // YASA AST 规则到 CWE 的映射
    switch (rule.toLowerCase()) {
      default:
        return 0;
    }
  }
}
