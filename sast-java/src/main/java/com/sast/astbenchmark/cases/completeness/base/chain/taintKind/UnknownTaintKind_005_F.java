package com.sast.astbenchmark.cases.completeness.base.chain.taintKind;

/**
 * Introduction 污点链路样本中的source点-source是否被污染未知-没有sink
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->污点状态->unknown taint->没有sink
// compose = !UnknownTaintKind_004_F.java && !UnknownTaintKind_005_F.java && !UnknownTaintKind_006_F.java
// bind_url =
// assession information end
public class UnknownTaintKind_005_F {
    public void unknownTaintKind_005_F(String name) {
        String sql = "select * from user where username = " + name;
    }
}
