package com.sast.astbenchmark.other_preference;

import com.sast.astbenchmark.common.utils.JDBCUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 污点链路样本中的污点分类，一定被source污染，且source传入sink
 * Level X
 * Date 2024-05-23
 */
// evaluation information start
// real case = true
// evaluation item  = 完整度->基础跟踪能力->污点链路完整度->污点状态->一定被source污染
// bind_url = completeness/base/chain/taintKind/MustTaintKind_001_T
// evaluation information end
@RestController
@RequestMapping("completeness/base/chain/taintKind")
public class MustTaintKind_001_T {
    @GetMapping("MustTaintKind_001_T")
    public void testcase(@RequestParam String name) {
        String sql = "select * from user where username = " + name;
        JDBCUtil.execSql(sql);
    }
}
