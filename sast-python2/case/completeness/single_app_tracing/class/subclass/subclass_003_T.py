# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
# scene introduction = 继承方法
# level = 2
# bind_url = completeness/single_app_tracing/class/subclass/subclass_003_T
# evaluation information end
import os


class Parent(object):
    def __init__(self, data):
        self.data = data

    def getData(self):
        return self.data


class Child(Parent):
    pass


def subclass_003_T(taint_src):
    obj = Child(taint_src)
    taint_sink(obj.getData())


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    subclass_003_T(taint_src)
