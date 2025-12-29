# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
# scene introduction = 一阶
# level = 2
# bind_url = completeness/single_app_tracing/function_call/generator_function/generator_function_001_F
# evaluation information end
import os


def generator_function_001_F(taint_src):
    def my_gen():
        a = yield 2
        b = yield (a + 2)
        yield (b + 3)

    g = my_gen()
    # 获取生成器的第一个值
    first_value = next(g)
    taint_sink(first_value)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    generator_function_001_F(taint_src)

