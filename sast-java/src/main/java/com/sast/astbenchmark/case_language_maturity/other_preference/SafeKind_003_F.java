package com.sast.astbenchmark.case_language_maturity.other_preference;

import com.sast.astbenchmark.common.utils.JDBCUtil;

/**
 * Introduction 污点链路样本中的污点分类，没有source，且有sink但未传入source
 * Level X
 * Date 2024-05-23
 */
// evaluation information start
// real case = false
// evaluation item  = 完整度->基础跟踪能力->污点链路完整度->污点状态->没有source
// bind_url =
// evaluation information end
public class SafeKind_003_F {
    public void testcase() {
        String sql = "select * from user where username = zhangsan";
        String sql2 = "select * from user where username = " + "zhangsan";
        JDBCUtil.execSql(sql2);
    }
}
