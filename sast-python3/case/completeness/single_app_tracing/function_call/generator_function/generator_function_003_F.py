# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
# scene introduction = 二阶
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/generator_function/generator_function_003_F
# evaluation information end
import os


def generator_function_003_F(taint_src):
    def my_gen():
        a = yield 2
        b = yield (a + 2)
        yield b + '_'

    g = my_gen()

    # 获取第一个yield值
    first_yield = next(g)

    # 向生成器发送值1并获取第二个yield值
    second_yield = g.send(first_yield)

    taint_sink(second_yield)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    generator_function_003_F(taint_src)

