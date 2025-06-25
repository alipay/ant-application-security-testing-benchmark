# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 位操作->或
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/bitwise_expression_or_002_F
# evaluation information end
import os


def bitwise_expression_or_002_F(taint_src):
    result = taint_src | 1
    taint_sink('_')


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = 7
    bitwise_expression_or_002_F(taint_src)

