package com.sast.astbenchmark.cases.completeness.base.chain.taintKind;

import com.sast.astbenchmark.common.utils.JDBCUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 污点链路样本中的source点-source可能被污染-source传入sink
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->source对象->source一定被污染->source传入sink
// compose = MayTaintKind_007_T.java && !MayTaintKind_008_F.java && !MayTaintKind_009_F.java
// bind_url = completeness/base/source/MayTaintKind_007_T
// assession information end
@RestController
@RequestMapping("completeness/base/source")
public class MayTaintKind_007_T {
    @GetMapping("MayTaintKind_007_T")
    public void aTaintCase(@RequestParam String name, @RequestParam Integer condition) {
        String sql;
        if (condition > 0) {
            sql = "select * from user where username = " + name;
        }
        else {
            sql = "select * from user where username = " + "zhangsan";
        }
        JDBCUtil.execSql(sql);
    }
}
