# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 多重继承
# level = 2+
# bind_url = completeness/single_app_tracing/class/complex_object/multiple_inheritance_002_F
# evaluation information end

import os

def multiple_inheritance_002_F(taint_src):
    class A:
        def __init__(self, data):
            self.data_a = data

    class B:
        def __init__(self, data):
            self.data_b = data 

    class C(A, B):
        def __init__(self, data):
            A.__init__(self, data)
            B.__init__(self, data)
            
        def get_result(self):
            return self.data_a + self.data_b 

    obj = C("_")
    taint_sink(obj.get_result())


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    multiple_inheritance_002_F(taint_src)