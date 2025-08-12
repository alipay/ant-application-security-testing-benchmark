# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/class/simple_object/simple_object_002_F
# evaluation information end
import os


def simple_object_002_F(taint_src):
    class A(object):
        def __init__(self, data):
            self.data = data

    obj = A(u'_')
    taint_sink(obj.data)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    simple_object_002_F(taint_src)

