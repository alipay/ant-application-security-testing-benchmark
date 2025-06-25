# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 解构赋值表达式->数组解构赋值->二维
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_005_T
# evaluation information end
import os


def destructuring_assignment_005_T(taint_src):
    arr = ['_', '_', [taint_src, '_']]
    a, b, (c, d) = arr  # 解构赋值，包括嵌套的列表
    taint_sink(c)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    destructuring_assignment_005_T(taint_src)

