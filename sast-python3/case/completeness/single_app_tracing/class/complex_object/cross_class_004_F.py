# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 跨类访问-对象别名
# level = 2
# bind_url = completeness/single_app_tracing/class/complex_object/cross_class_004_F
# evaluation information end
import os


def cross_class_004_F(taint_src):
    class A:
        def __init__(self):
            self.topData = None

        def createB(self):
            self.topData = B()
            return self.topData

    class B:
        def __init__(self):
            self.data = ''

        def set_data(self, data):
            self.data = data

    a = A()
    b = a.createB()
    b.set_data('_')  # 固定值 '_'
    taint_sink(a.topData.data)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    cross_class_004_F(taint_src)

