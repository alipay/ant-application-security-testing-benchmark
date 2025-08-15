# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
# scene introduction = 普通
# level = 2
# bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_normal_value_passing_001_F
# evaluation information end
import os


def return_normal_value_passing_001_f(taint_src):
    def process(src):
        return '_'  # 直接返回传入的参数

    data = process(taint_src)
    taint_sink(data)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    return_normal_value_passing_001_f(taint_src)

