# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
# scene introduction = 匿名函数为参数
# level = 2
# bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_008_T
# evaluation information end
import os


def higher_order_function_008_T(taint_src):
    def higher_order_function(callback):
        return callback(taint_src, u'_')

    result = higher_order_function(lambda a, b: a + b)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    higher_order_function_008_T(taint_src)

