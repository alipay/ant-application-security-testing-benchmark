package com.sast.astbenchmark.cases.completeness.base.chain.taintKind;

import com.sast.astbenchmark.common.utils.JDBCUtil;

/**
 * Introduction 污点链路样本中的source点-source是否被污染未知-source传入sink
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->source对象->source是否被污染未知->source传入sink
// compose = !UnknownTaintKind_004_F.java && !UnknownTaintKind_005_F.java && !UnknownTaintKind_006_F.java
// bind_url =
// assession information end
public class UnknownTaintKind_004_F {
    public void aTaintCase(String name) {
        String sql = "select * from user where username = " + name;
        JDBCUtil.execSql(sql);
    }
}