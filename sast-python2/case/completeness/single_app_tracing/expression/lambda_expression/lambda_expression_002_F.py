# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->Lambda表达式
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/expression/lambda_expression/lambda_expression_002_F
# evaluation information end
import os


def lambda_expression_002_F(taint_src):
    result = u''
    def lambda_func(a): return u'_'
    result = lambda_func(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    lambda_expression_002_F(taint_src)

