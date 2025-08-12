# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
# scene introduction = 三元运算符
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_ternary_002_F
# evaluation information end

import os

def conditional_ternary_002_F(taint_src):
    result = taint_src if False else u"_"  # 条件为假时未使用污点
    taint_sink(result)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    conditional_ternary_002_F(taint_src)

