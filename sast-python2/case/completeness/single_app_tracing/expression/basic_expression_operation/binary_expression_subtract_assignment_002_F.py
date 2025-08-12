# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->减等
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_subtract_assignment_002_F
# evaluation information end

import os


def binary_expression_subtract_assignment_002_F(taint_src):
    result = 10  # 初始化一个数值用于演示减等操作
    result -= 3  
    taint_sink(result)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = 7  
    binary_expression_subtract_assignment_002_F(taint_src)
