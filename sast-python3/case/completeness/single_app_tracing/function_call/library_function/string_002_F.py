# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
# scene introduction = 字符串库
# level = 2
# bind_url = completeness/single_app_tracing/function_call/library_function/string_002_F
# evaluation information end
import os


def string_002_F(taint_src):
    process('_')


def process(arg):
    result = arg.strip()
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    string_002_F(taint_src)

