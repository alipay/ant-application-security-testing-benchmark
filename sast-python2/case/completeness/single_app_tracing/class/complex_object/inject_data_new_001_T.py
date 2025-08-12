# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 实例化中注入污染源
# level = 2+
# bind_url = completeness/single_app_tracing/class/complex_object/inject_data_new_001_T
# evaluation information end

import os

def inject_data_new_001_T(taint_src):
    class A(object): 
        def __new__(cls, data):
            instance = super(A, cls).__new__(cls)  
            instance.data = data  
            return instance

    obj1 = A(taint_src)
    taint_sink(obj1.data)

def taint_sink(o):
    os.system(o)

if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    inject_data_new_001_T(taint_src)

