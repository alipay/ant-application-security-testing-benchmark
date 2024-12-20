package com.sast.astbenchmark.case_language_maturity.other_preference;

import com.sast.astbenchmark.common.utils.JDBCUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 污点链路样本中的污点分类，一定被source污染，且有sink但未传入source
 * Level X
 * Date 2024-05-23
 */
// evaluation information start
// real case = false
// evaluation item  = 完整度->基础跟踪能力->污点链路完整度->污点状态->一定被source污染
// bind_url = completeness/base/chain/taintKind/MustTaintKind_003_F
// evaluation information end
@RestController
@RequestMapping("completeness/base/chain/taintKind")
public class MustTaintKind_003_F {
    @GetMapping("MustTaintKind_003_F")
    public void testcase(@RequestParam String name) {
        String sql = "select * from user where username = " + name;
        String sql2 = "select * from user where username = " + "zhangsan";
        JDBCUtil.execSql(sql2);
    }
}
