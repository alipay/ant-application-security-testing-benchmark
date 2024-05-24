package com.sast.astbenchmark.cases.completeness.base.chain.taintKind;

import com.sast.astbenchmark.common.utils.JDBCUtil;

/**
 * Introduction 污点链路样本中的source点-source未被污染-有sink但未传入source
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->source对象->source未被污染->有sink但未传入source
// compose = !SafeKind_010_F.java && !SafeKind_011_F.java && !SafeKind_012_F.java
// bind_url =
// assession information end
public class SafeKind_012_F {
    public void aTaintCase() {
        String sql = "select * from user where username = zhangsan";
        String sql2 = "select * from user where username = " + "zhangsan";
        JDBCUtil.execSql(sql2);
    }
}