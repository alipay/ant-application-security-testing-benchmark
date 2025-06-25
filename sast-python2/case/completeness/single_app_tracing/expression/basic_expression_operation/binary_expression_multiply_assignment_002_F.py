# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->乘等
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_multiply_assignment_002_F
# evaluation information end

import os


def binary_expression_multiply_assignment_002_F(taint_src):
    result = 5  # 初始化一个数值用于演示乘等操作
    result *= 3
    taint_sink(result)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = 7  # 示例输入，应确保可以被安全地转换为int
    binary_expression_multiply_assignment_002_F(taint_src)
