# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
# scene introduction = 继承属性
# level = 2
# bind_url = completeness/single_app_tracing/class/subclass/subclass_002_F
# evaluation information end
import os


class BaseObject(object):
    def __init__(self, data):
        self.data = "safe_value"


class DerivedObject(BaseObject):
    pass


def subclass_002_F(taint_src):
    obj = DerivedObject(taint_src)
    taint_sink(obj.data)


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    subclass_002_F(taint_src)
