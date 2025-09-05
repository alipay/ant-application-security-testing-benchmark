# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
# scene introduction = 二阶
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/generator_function/generator_function_004_T
# evaluation information end
import os


def generator_function_004_T(taint_src):
    def my_gen():
        a = yield taint_src
        b = yield (a + u"2")
        yield b + u'_'

    g = my_gen()

    # 获取第一个yield值
    first_yield = g.next()

    # 向生成器发送值并获取第二个yield值
    second_yield = g.send(first_yield)

    taint_sink(second_yield)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    generator_function_004_T(taint_src)

