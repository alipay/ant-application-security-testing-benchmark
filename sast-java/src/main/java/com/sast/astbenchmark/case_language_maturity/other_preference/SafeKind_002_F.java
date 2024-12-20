package com.sast.astbenchmark.case_language_maturity.other_preference;

/**
 * Introduction 污点链路样本中的污点分类，没有source，且没有sink
 * Level X
 * Date 2024-05-23
 */
// evaluation information start
// real case = false
// evaluation item  = 完整度->基础跟踪能力->污点链路完整度->污点状态->没有source
// bind_url =
// evaluation information end
public class SafeKind_002_F {
    public void testcase() {
        String sql = "select * from user where username = zhangsan";
    }
}
