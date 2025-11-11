# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 多级继承
# level = 2+
# bind_url = completeness/single_app_tracing/class/complex_object/multi_level_inheritance_002_F
# evaluation information end

import os

def multi_level_inheritance_002_F(taint_src):
    class C:
        def __init__(self, data):
            self.data_c = data  # 最底层存储数据

    class B(C):
        def __init__(self, data):
            super().__init__(data)  # 调用C的构造函数
            self.data_b = "_B"  # 添加B自己的数据

    class A(B):
        def __init__(self, data):
            super().__init__(data)  # 调用B的构造函数 → 调用C的构造函数
            self.data_a =  "_A"  # 添加A自己的数据
            
        def get_combined_data(self):
            return self.data_c + self.data_b + self.data_a 

    obj = A("_")  
    taint_sink(obj.get_combined_data())


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    multi_level_inheritance_002_F(taint_src)