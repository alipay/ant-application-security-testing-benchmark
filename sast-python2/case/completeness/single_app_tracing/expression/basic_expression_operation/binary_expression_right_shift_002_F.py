# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->右移赋值
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_right_shift_002_F
# evaluation information end
import os


def binary_expression_right_shift_002_F(taint_src):
    x = 8
    x >>= taint_src
    taint_sink(1)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = 2
    binary_expression_right_shift_002_F(taint_src)
