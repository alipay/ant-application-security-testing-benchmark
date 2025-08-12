# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
# scene introduction = 构造函数
# level = 2
# bind_url = completeness/single_app_tracing/function_call/override/constructor_extends_001_T
# evaluation information end
import os

def constructor_extends_001_T(taint_src):
    derived = DerivedClass(taint_src)
    derived.process()

class BaseClass(object):
    def __init__(self, data):
        self.data = data

    def get_data(self):
        return self.data

    def process(self):
        pass


class DerivedClass(BaseClass):
    def __init__(self, data):
        super(DerivedClass, self).__init__(data)

    def process(self):
        taint_sink(self.get_data())


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    constructor_extends_001_T(taint_src)

