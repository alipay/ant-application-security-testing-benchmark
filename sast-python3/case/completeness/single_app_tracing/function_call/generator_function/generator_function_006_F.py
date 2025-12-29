# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
# scene introduction = 嵌套调用
# level = 2
# bind_url = completeness/single_app_tracing/function_call/generator_function/generator_function_006_F
# evaluation information end
import os


def generator_function_006_F(taint_src):
    def outer_gen(taint_src):
        inner_gen = my_inner_gen(taint_src)
        first = next(inner_gen)
        yield first

    def my_inner_gen(t):
        a = yield t  # 污染源通过参数传递
        yield a + "3"

    g = outer_gen("_")
    taint_sink(next(g))  # 跟踪外层生成器的首次 yield


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    generator_function_006_F(taint_src)

