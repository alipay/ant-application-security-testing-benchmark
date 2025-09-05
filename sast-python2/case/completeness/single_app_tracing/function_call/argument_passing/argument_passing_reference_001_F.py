# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 引用传递
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_reference_001_F
# evaluation information end
import os


def argument_passing_reference_001_F(taint_src):
    obj = {u'data': u'_'}
    process(obj)
    taint_sink(obj[u'data'])


def process(obj):
    obj[u'data'] = u'_'


def taint_sink(o): 
    os.system(o)
    pass


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    argument_passing_reference_001_F(taint_src)

