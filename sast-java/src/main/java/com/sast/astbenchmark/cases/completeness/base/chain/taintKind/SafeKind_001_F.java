package com.sast.astbenchmark.cases.completeness.base.chain.taintKind;

import com.sast.astbenchmark.common.utils.JDBCUtil;

/**
 * Introduction 污点链路样本中的污点分类，没有source，且source传入sink
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->污点状态->没有source
// compose = !SafeKind_001_F.java && !SafeKind_002_F.java && !SafeKind_003_F.java
// bind_url =
// assession information end
public class SafeKind_001_F {
    public void testcase() {
        String sql = "select * from user where username = zhangsan";
        JDBCUtil.execSql(sql);
    }
}
