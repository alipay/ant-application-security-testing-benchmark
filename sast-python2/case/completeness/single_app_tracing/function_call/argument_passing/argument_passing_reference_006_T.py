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
    objA = {u'name': taint_src}
    objB = {u'name': u'Bob'}

    swap(objA, objB)
    taint_sink(objB[u'name'])


def swap(obj1, obj2):
    temp = obj1[u'name']
    obj1[u'name'] = obj2[u'name']
    obj2[u'name'] = temp


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    argument_passing_reference_006_T(taint_src)

