package com.alipay.xast.score.models;

public class ThreadFlowLocation {

  private String FileName;

  /**
   * 文件路径
   * 对应 physicalLocation.artifactLocation.uri
   */
  private String filePath;

  /**
   * 开始行号
   * 对应 physicalLocation.region.startLine
   */
  private Integer startLine;

  /**
   * 开始列号
   * 对应 physicalLocation.region.startColumn
   */
  private Integer startColumn;


  /**
   * 结束行号
   * 对应 physicalLocation.region.endLine
   */
  private Integer endLine;

  /**
   * 结束列号
   * 对应 physicalLocation.region.endColumn
   */
  private Integer endColumn;


  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public Integer getStartLine() {
    return startLine;
  }

  public void setStartLine(Integer startLine) {
    this.startLine = startLine;
  }

  public Integer getStartColumn() {
    return startColumn;
  }

  public void setStartColumn(Integer startColumn) {
    this.startColumn = startColumn;
  }

  public Integer getEndLine() {
    return endLine;
  }

  public void setEndLine(Integer endLine) {
    this.endLine = endLine;
  }

  public Integer getEndColumn() {
    return endColumn;
  }

  public void setEndColumn(Integer endColumn) {
    this.endColumn = endColumn;
  }

  public String getFileName() {
    return FileName;
  }

  public void setFileName(String fileName) {
    FileName = fileName;
  }
}
