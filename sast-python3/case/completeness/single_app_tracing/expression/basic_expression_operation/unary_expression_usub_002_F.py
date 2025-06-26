# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 一元运算->负号
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/unary_expression_usub_002_F
# evaluation information end

import os


def unary_expression_usub_002_F(taint_src):
    result = -1
    taint_sink(result)


def taint_sink(o):
    os.system(str(o))  


if __name__ == '__main__':
    taint_src = 7
    unary_expression_usub_002_F(taint_src)
