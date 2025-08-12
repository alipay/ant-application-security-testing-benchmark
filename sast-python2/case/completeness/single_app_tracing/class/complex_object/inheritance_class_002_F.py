# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 继承与多态
# level = 2
# bind_url = completeness/single_app_tracing/class/complex_object/inheritance_class_002_F
# evaluation information end
import os

def inheritance_class_002_F(taint_src):
    class Base(object): 
        def __init__(self, data):
            self.data = "_"

        def get_data(self):
            return self.data

    class Child(Base):
        def __init__(self, data):
            Base.__init__(self, data)  # 改为显式调用父类构造函数

        def get_child_data(self):
            return self.data

    obj = Child(taint_src)
    taint_sink(obj.get_data())  # 通过子类方法访问父类属性

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    inheritance_class_002_F(taint_src)  # 输出"Child: Tainted"