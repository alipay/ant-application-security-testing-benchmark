package com.iast.astbenchmark.analyser.service;

import com.iast.astbenchmark.analyser.bean.CaseDataCollectResultBean;
import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;

import java.sql.SQLException;
import java.util.List;

/**
 * 用于检查结果分析，以及分析结果存储
 * 返回ID用户报告下载
 */
public interface DataAnalysisService {
    Long  doAnalysisAndDB(VendorEnum vendorEnum) throws SQLException;
    CaseDataCollectResultBean searchResult(Long id) throws SQLException;
    CaseDataCollectResultBean searchResultbyReportId(String  reportID) throws SQLException;
    List<String> getAllReportId(String vendor);
}
