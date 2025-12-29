# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
# scene introduction = 一阶
# level = 2
# bind_url = completeness/single_app_tracing/function_call/generator_function/generator_function_002_T
# evaluation information end
import os


def generator_function_002_T(taint_src):
    def my_gen():
        a = yield taint_src
        b = yield (a + 2)
        yield (b + 3)

    g = my_gen()
    # 获取生成器的第一个值
    first_value = g.next()
    taint_sink(first_value)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    generator_function_002_T(taint_src)

