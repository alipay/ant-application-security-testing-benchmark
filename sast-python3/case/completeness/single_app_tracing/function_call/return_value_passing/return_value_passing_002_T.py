# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
# scene introduction = 普通
# level = 2
# bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_value_passing_002_T
# evaluation information end
import os


def return_value_passing_002_T(taint_src):
    data = process(taint_src)
    taint_sink(data)


def process(src):
    return src


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    return_value_passing_002_T(taint_src)

