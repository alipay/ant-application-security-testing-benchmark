# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->整除
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_floor_div_case_001_T
# evaluation information end

import os

def binary_expression_floor_div_case_001_T(taint_src):
    # 直接进行整除操作并将结果传递给sink
    taint_sink(taint_src // 2)

def taint_sink(o):
    os.system(unicode(o))

if __name__ == u"__main__":
    taint_src = 7  
    binary_expression_floor_div_case_001_T(taint_src)
