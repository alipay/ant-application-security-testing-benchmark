# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 解构赋值表达式->数组解构赋值
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_002_F
# evaluation information end
import os


def destructuring_assignment_002_F(taint_src):
    arr = ['_', '_', taint_src]
    a, b, result = arr  # 解构赋值
    taint_sink(a)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    destructuring_assignment_002_F(taint_src)

