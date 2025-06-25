# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 引用传递
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_reference_002_T
# evaluation information end
import os


def argument_passing_reference_002_T(taint_src):
    obj = {'data': '_'}
    process(obj, taint_src)  # 将__taint_src作为参数传递给process函数
    taint_sink(obj['data'])


def process(obj, taint_src):  # 增加参数__taint_src
    obj['data'] = taint_src


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    argument_passing_reference_002_T(taint_src)

