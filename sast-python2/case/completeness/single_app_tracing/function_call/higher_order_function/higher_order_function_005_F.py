# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
# scene introduction = 三阶
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_005_F
# evaluation information end
import os


def higher_order_function_005_F(taint_src):
    def f(g, u, a, b):
        return g(u, a, b)

    def g(u, a, b):
        c = u'_'
        return u(a, b, c)

    def u(a, b, c):
        def inner():
            return a + b + c

        return inner

    taint_sink(f(g, u, u'aa', u'_')())


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    higher_order_function_005_F(taint_src)

