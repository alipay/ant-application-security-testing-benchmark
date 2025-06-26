# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->地板除法赋值
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_floor_division_002_F
# evaluation information end
import os


def binary_expression_floor_division_002_F(taint_src):
    x = 10
    x //= taint_src
    taint_sink(1)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = 3
    binary_expression_floor_division_002_F(taint_src)
