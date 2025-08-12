# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 跨类访问-成员变量
# level = 2
# bind_url = completeness/single_app_tracing/class/complex_object/cross_class_002_F
# evaluation information end
import os


def cross_class_002_F(taint_src):
    class A(object):
        def __init__(self):
            self.data = u'_'

        def get_data(self):
            return self.data

    class B(object):
        def __init__(self):
            a_instance = A()
            self.data = a_instance.get_data()

    obj = B()
    taint_sink(obj.data)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    cross_class_002_F(taint_src)

