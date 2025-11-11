# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 父类init函数自动调用
# level = 2+
# bind_url = completeness/single_app_tracing/class/complex_object/no_init_child_class_001_T
# evaluation information end

import os

def no_init_child_class_001_T(taint_src):
    class Parent:
        def __init__(self, data):
            # 父类构造函数直接接收外部数据
            self.data = data

    class Child(Parent):
        # 子类没有定义__init__方法，会自动调用父类的__init__
        def process_data(self):
            # 子类方法处理从父类继承的污染数据
            return  self.data 

    # 创建子类实例时，自动调用Parent.__init__(taint_src)
    obj = Child(taint_src)  
    
    # 通过子类方法访问继承的污染属性
    taint_sink(obj.process_data())

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    no_init_child_class_001_T(taint_src)