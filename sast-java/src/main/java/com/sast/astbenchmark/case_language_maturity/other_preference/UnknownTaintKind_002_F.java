package com.sast.astbenchmark.case_language_maturity.other_preference;

/**
 * Introduction 污点链路样本中的污点分类，不确定是否被污染，且没有sink
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->污点状态->不确定是否被污染
// compose = !UnknownTaintKind_001_F.java && !UnknownTaintKind_002_F.java && !UnknownTaintKind_003_F.java
// bind_url =
// assession information end
public class UnknownTaintKind_002_F {
    public void testcase(String name) {
        String sql = "select * from user where username = " + name;
    }
}
