# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->海象运算
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_namedexpr_basic_001_T
# evaluation information end

import os

def binary_expression_namedexpr_basic_001_T(__taint_src):
    (x := __taint_src)
    taint_sink(x)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    __taint_src = 10
    binary_expression_namedexpr_basic_001_T(__taint_src)
