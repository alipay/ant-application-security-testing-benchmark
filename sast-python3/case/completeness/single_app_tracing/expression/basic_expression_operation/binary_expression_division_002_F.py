# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->除法
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_division_002_F
# evaluation information end

import os


def binary_expression_division_002_F(taint_src):
   
    result = 7
    result =result / taint_src
    taint_sink("_")


def taint_sink(o):
    os.system(str(o))  


if __name__ == '__main__':
    taint_src = 7
    binary_expression_division_002_F(taint_src)
