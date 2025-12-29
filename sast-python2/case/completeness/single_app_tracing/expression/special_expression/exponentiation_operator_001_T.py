# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 指数运算符
# level = 2
# bind_url = completeness/single_app_tracing/expression/special_expression/exponentiation_operator_001_T
# evaluation information end
import os

def exponentiation_operator_001_T(taint_src):
    result = taint_src ** 2
    taint_sink(result)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = 2
    exponentiation_operator_001_T(taint_src)

