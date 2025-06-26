# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
# scene introduction = 简单匿名函数
# level = 2
# bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_002_T
# evaluation information end

import os

def anonymous_function_002_T(taint_src):
    process = lambda input: taint_sink(input)
    process(taint_src)

def taint_sink(o):
    os.system(o)

if __name__ == '__main__':
    taint_src = "taint_src_value"
    anonymous_function_002_T(taint_src)
