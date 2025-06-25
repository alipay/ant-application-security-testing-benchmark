# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 引用传递map
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_reference_006_T
# evaluation information end
import os


def argument_passing_reference_006_T(taint_src):
    objA = {'name': taint_src}
    objB = {'name': 'Bob'}

    swap(objA, objB)
    taint_sink(objB['name'])


def swap(obj1, obj2):
    temp = obj1['name']
    obj1['name'] = obj2['name']
    obj2['name'] = temp


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    argument_passing_reference_006_T(taint_src)

