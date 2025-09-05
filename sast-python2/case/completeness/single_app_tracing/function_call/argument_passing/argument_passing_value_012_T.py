# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 值传递嵌套函数
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_012_T
# evaluation information end
import os


def argument_passing_value_012_T(taint_src):
    def outer(input):
        def inner(inner_input):
            taint_sink(inner_input)

        inner(input)

    outer(taint_src)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    argument_passing_value_012_T(taint_src)

