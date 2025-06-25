# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 运行时动态创建实例
# level = 2+
# bind_url = completeness/single_app_tracing/class/complex_object/inject_data_new_006_F
# evaluation information end

import os

def inject_data_new_006_F(taint_src):

    class A:
        def data_transform(self):
            return taint_src

    class B:
        def data_transform(self):
            return "_"


    class Factory:
        def __new__(cls, type):
            # 通过不同的type 返回不同的实例
            if type == "A":
                return A()
            else:
                return B()


    obj = Factory("B")
    taint_sink(obj.data_transform())


def taint_sink(o):
   os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    inject_data_new_006_F(taint_src)