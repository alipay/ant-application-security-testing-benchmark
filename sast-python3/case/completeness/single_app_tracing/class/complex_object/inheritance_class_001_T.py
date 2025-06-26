# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 继承与多态
# level = 2
# bind_url = completeness/single_app_tracing/class/complex_object/inheritance_class_001_T
# evaluation information end
import os


def inheritance_class_001_T(taint_src):
    class Base:
        def __init__(self, data):
            self.data = data

        def get_data(self):
            return self.data

    class Child(Base):
        def __init__(self, data):
            super().__init__(data)

        def get_child_data(self):
            return self.data

    obj = Child(taint_src)
    taint_sink(obj.get_child_data())  # 通过子类方法访问父类属性

def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    inheritance_class_001_T(taint_src)  # 输出"Child: Tainted"

