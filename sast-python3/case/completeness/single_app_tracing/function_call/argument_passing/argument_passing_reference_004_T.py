# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 引用传递数组
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_reference_004_T
# evaluation information end
import os


def argument_passing_reference_004_T(taint_src):
    def process(input_arr):
        input_arr[0] = taint_src

    arr = ['_']
    process(arr)
    taint_sink(arr)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    argument_passing_reference_004_T(taint_src)

