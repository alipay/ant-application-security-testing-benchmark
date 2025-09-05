# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->条件表达式
# scene introduction = 三目运算
# level = 2
# bind_url = completeness/single_app_tracing/expression/conditional_expression/conditional_expression_001_T
# evaluation information end
import os


def conditional_expression_001_T(taint_src):
    result = u''
    result = taint_src if True else u'aa'
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    conditional_expression_001_T(taint_src)

