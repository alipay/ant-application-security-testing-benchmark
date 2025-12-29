# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
# scene introduction = 二阶
# level = 2
# bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_004_T
# evaluation information end
import os


def higher_order_function_004_T(taint_src):
    def f(g, a, b):
        return g(a, b)

    def g(a, b):
        c = '_'
        return lambda: a + b + c

    taint_sink(f(g, taint_src, '_')())


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    higher_order_function_004_T(taint_src)

