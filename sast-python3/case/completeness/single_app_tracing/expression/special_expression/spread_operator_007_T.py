# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 扩展运算符
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/spread_operator_007_T
# evaluation information end
import os


def spread_operator_007_T(taint_src):
    collectArgs(['prefix', taint_src, 'suffix'])


def collectArgs(*args):
    taint_sink(args)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    spread_operator_007_T(taint_src)

