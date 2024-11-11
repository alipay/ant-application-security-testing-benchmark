package com.sast.astbenchmark.case_language_maturity.other_preference;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 污点链路样本中的污点分类，一定被source污染，且没有sink
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->污点状态->一定被source污染
// compose = MustTaintKind_001_T.java && !MustTaintKind_002_F.java && !MustTaintKind_003_F.java
// bind_url = completeness/base/chain/taintKind/MustTaintKind_002_F
// assession information end
@RestController
@RequestMapping("completeness/base/chain/taintKind")
public class MustTaintKind_002_F {
    @GetMapping("MustTaintKind_002_F")
    public void testcase(@RequestParam String name) {
        String sql = "select * from user where username = " + name;
    }
}
