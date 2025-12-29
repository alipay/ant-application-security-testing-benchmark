# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 实例化中注入污染源
# level = 2
# bind_url = completeness/single_app_tracing/class/complex_object/inject_data_new_001_T
# evaluation information end

import os
# __new__ 是一个静态方法，它在类的实例化过程中被调用，创建过程中的步骤比 __init__ 更早。
# 在涉及到更复杂的对象创建逻辑时，使用的较多
def inject_data_new_001_T(taint_src):
    class A:
        def __new__(cls, data):
            instance = super().__new__(cls)
            instance.data = data  # 直接在__new__中赋值
            return instance

    obj1 = A(taint_src)
    taint_sink(obj1.data)

def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    inject_data_new_001_T(taint_src)

