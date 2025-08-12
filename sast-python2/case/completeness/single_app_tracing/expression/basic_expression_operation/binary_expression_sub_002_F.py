# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->减
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_sub_002_F
# evaluation information end


import os


def binary_expression_sub_002_F(taint_src):
    result = 2 - 1
    taint_sink(result)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    binary_expression_sub_002_F(taint_src)

