# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->加等
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_add_assignment_002_F
# evaluation information end
import os


def binary_expression_add_assignment_002_T(taint_src):
    aa = "aa"
    result = '_'
    result += aa  # 使用传入的参数 aa
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    binary_expression_add_assignment_002_T(taint_src) 

