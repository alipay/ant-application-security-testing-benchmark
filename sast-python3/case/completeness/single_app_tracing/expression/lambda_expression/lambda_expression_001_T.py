# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->Lambda表达式
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/expression/lambda_expression/lambda_expression_001_T
# evaluation information end
import os


def lambda_expression_001_T(taint_src):
    result = ''
    def lambda_func(a): return a
    result = lambda_func(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    lambda_expression_001_T(taint_src)

