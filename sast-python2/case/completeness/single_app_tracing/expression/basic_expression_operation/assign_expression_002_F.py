# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 赋值表达式
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/assign_expression_002_F
# evaluation information end
import os


def assign_expression_002_F(taint_src):
    result = u'aa'
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    assign_expression_002_F(taint_src)

