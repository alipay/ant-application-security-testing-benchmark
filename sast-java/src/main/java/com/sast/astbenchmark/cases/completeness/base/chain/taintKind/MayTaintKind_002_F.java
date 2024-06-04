package com.sast.astbenchmark.cases.completeness.base.chain.taintKind;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 污点链路样本中的污点分类，可能被source污染，且没有sink
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->污点状态->可能被source污染
// compose = MayTaintKind_001_T.java && !MayTaintKind_002_F.java && !MayTaintKind_003_F.java
// bind_url = completeness/base/chain/taintKind/MayTaintKind_002_F
// assession information end
@RestController
@RequestMapping("completeness/base/chain/taintKind")
public class MayTaintKind_002_F {
    @GetMapping("MayTaintKind_002_F")
    public void testcase(@RequestParam String name, @RequestParam Integer condition) {
        String sql;
        if (condition > 0) {
            sql = "select * from user where username = " + name;
        }
        else {
            sql = "select * from user where username = " + "zhangsan";
        }
    }
}
