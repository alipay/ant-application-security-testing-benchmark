# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 逻辑表达式->或表达式
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/logic_expression_or_001_T
# evaluation information end
import os


def logic_expression_or_001_T(taint_src):
    result = taint_src or u"_"  
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    logic_expression_or_001_T(taint_src)

