package com.sast.astbenchmark.cases.completeness.base.chain.taintKind;

import com.sast.astbenchmark.common.utils.JDBCUtil;

/**
 * Introduction 污点链路样本中的污点分类，source污染状态未知，且source传入sink
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->污点状态->source污染状态未知
// compose = !UnknownTaintKind_001_F.java && !UnknownTaintKind_002_F.java && !UnknownTaintKind_003_F.java
// bind_url =
// assession information end
public class UnknownTaintKind_001_F {
    public void testcase(String name) {
        String sql = "select * from user where username = " + name;
        JDBCUtil.execSql(sql);
    }
}
