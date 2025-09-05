# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 运行时动态创建实例
# level = 2+
# bind_url = completeness/single_app_tracing/class/complex_object/inject_data_new_005_T
# evaluation information end

import os

def inject_data_new_005_T(taint_src):

    class A(object):
        def data_transform(self):
            return taint_src

    class B(object):
        def data_transform(self):
            return u"_"

    class Factory(object):
        def __new__(cls, type):
            if type == "A":
               return A()
            else:
               return B()


    obj = Factory("A")
    taint_sink(obj.data_transform())


def taint_sink(o):
   os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    inject_data_new_005_T(taint_src)