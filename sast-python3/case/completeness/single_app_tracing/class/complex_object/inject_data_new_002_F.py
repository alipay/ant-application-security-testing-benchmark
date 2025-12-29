# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 实例化中注入污染源
# level = 2
# bind_url = completeness/single_app_tracing/class/complex_object/inject_data_new_002_F
# evaluation information end

import os

def inject_data_new_002_F(taint_src):
    class A:
        def __new__(cls, data):
            instance = super().__new__(cls)
            instance.data = data  # 直接在__new__中赋值
            return instance

    obj2 = A("_")
    taint_sink(obj2.data)

def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    inject_data_new_002_F(taint_src)

