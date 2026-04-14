package com.alipay.xast.score.parsers;

import com.alipay.xast.score.ResultFile;
import com.alipay.xast.score.models.TestCaseResult;
import com.alipay.xast.score.TestSuiteResults;
import com.alipay.xast.score.models.ThreadFlowLocation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SarifReader extends Reader {
  private static final Logger logger = LoggerFactory.getLogger(SarifReader.class);

  private static final int PATH_DEEP = 1;

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
    return resultFile.filename().endsWith(".sarif");
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
    String toolName = getToolName(resultFile);
    TestSuiteResults results = new TestSuiteResults(toolName, false, TestSuiteResults.ToolType.SAST);

    results.setTime(resultFile.file());

    JSONObject json = resultFile.json();

    results.setToolVersion(json.getString("version"));

    List<TestCaseResult> testCaseResults = handleResultFileToCaseFileList(resultFile);

    for (TestCaseResult testCase : testCaseResults) {
      results.getTcrs().add(testCase);
    }

    return results;
  }

  // 文件夹解析器
  private TestSuiteResults dirParse(List<ResultFile> resultFiles) throws Exception {
    String toolName = getToolName(resultFiles.get(0));
    TestSuiteResults results = new TestSuiteResults(toolName, false, TestSuiteResults.ToolType.SAST);

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

        List<TestCaseResult> testCaseResults = handleResultFileToCaseFileList(resultFile);
        for (TestCaseResult testCase : testCaseResults) {
          results.getTcrs().add(testCase);
        }
      }
    }
    return results;
  }

  // 获取工具名称
  private String getToolName(ResultFile resultFile) {
    String name = "yasa";
    try {
      JSONObject json = resultFile.json();
      JSONArray runs = json.getJSONArray("runs");
      JSONObject run = runs.getJSONObject(0);
      name = run.getJSONObject("tool").getJSONObject("driver").getString("name");
    } catch (Exception e) {
      logger.error("获取工具名称失败", e);
    }
    return name;
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

  // 处理结果文件返回测试用例文件集合
  private List<TestCaseResult> handleResultFileToCaseFileList(ResultFile resultFile) {
    JSONObject json = resultFile.json();
    JSONArray runs = json.getJSONArray("runs");

    List<TestCaseResult> testCaseResults = new ArrayList<>();

    // 用于给 case 文件名去重
    Set<String> set = new HashSet<>();

    // 收集所有的 locations
    List<List<ThreadFlowLocation>> allLocations = IntStream.range(0, runs.length())
            .mapToObj(runs::getJSONObject)
            .flatMap(run -> toStream(run.getJSONArray("results")))
            .flatMap(result -> toStream(result.getJSONArray("codeFlows")))
            .flatMap(codeFlow -> toStream(codeFlow.getJSONArray("threadFlows")))
            .map(threadFlow -> threadFlow.getJSONArray("locations"))
            .map(this::toThreadFlowLocation)
            .collect(Collectors.toList());

    for (List<ThreadFlowLocation> locations : allLocations) {
      for (int i = 0; i < locations.size(); i++) {
        // 如果超过了 PATH_DEEP，就跳出循环
        if (i > PATH_DEEP) {
          break;
        }
        ThreadFlowLocation location = locations.get(i);
        set.add(location.getFileName());
      }
    }

    // 文件名去重
    for (String testCaseName : set) {
      TestCaseResult testCase = new TestCaseResult();
      testCase.setTestCaseName(testCaseName);
      testCaseResults.add(testCase);
    }

    return testCaseResults;
  }

  // 处理成流
  private Stream<JSONObject> toStream(JSONArray array) {
    return IntStream.range(0, array.length())
            .mapToObj(array::getJSONObject);
  }

  // 构造路径流实体类
  private List<ThreadFlowLocation> toThreadFlowLocation(JSONArray locations) {
    List<ThreadFlowLocation> threadFlowLocations = new ArrayList<>();
    for (int i = 0; i < locations.length(); i++) {
      JSONObject location = locations.getJSONObject(i).getJSONObject("location");
      JSONObject artifactLocation = location.getJSONObject("physicalLocation").getJSONObject("artifactLocation");
      JSONObject region = location.getJSONObject("physicalLocation").getJSONObject("region");

      ThreadFlowLocation threadFlowLocation = new ThreadFlowLocation();
      threadFlowLocation.setFilePath(artifactLocation.getString("uri"));
      threadFlowLocation.setFileName(extractTestCaseName(artifactLocation.getString("uri")));

      if (region.has("startLine")) {
        threadFlowLocation.setStartLine(region.getInt("startLine"));
      }
      if (region.has("startColumn")) {
        threadFlowLocation.setStartColumn(region.getInt("startColumn"));
      }
      if (region.has("endLine")) {
        threadFlowLocation.setEndLine(region.getInt("endLine"));
      }
      if (region.has("endColumn")) {
        threadFlowLocation.setEndColumn(region.getInt("endColumn"));
      }

      threadFlowLocations.add(threadFlowLocation);
    }
    return threadFlowLocations;
  }

  // 获取检测工具名称
//  private Boolean isYASA(JSONObject run) {
//    return run.getJSONObject("tool").getJSONObject("driver").getString("name").equals("yasa");
//  }

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
