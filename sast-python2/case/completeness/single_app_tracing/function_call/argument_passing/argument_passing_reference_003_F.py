# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 引用传递数组
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_reference_003_F
# evaluation information end
import os


def argument_passing_reference_003_F(taint_src):
    arr = [u'_']
    process(arr)  # 现在可以正确调用process函数
    taint_sink(arr)


def process(input_arr):
    input_arr[0] = u'_'


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    argument_passing_reference_003_F(taint_src)

