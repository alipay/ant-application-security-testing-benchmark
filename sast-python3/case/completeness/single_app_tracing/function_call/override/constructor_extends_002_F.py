# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
# scene introduction = 构造函数
# level = 2
# bind_url = completeness/single_app_tracing/function_call/override/constructor_extends_002_F
# evaluation information end
import os


class BaseClass:
    def __init__(self, data):
        self.data = data

    def getData(self):
        return self.data

    def process(self):
        pass


class DerivedClass(BaseClass):
    def __init__(self, data):
        super().__init__(data)

    def process(self):
        taint_sink(self.getData())


def constructor_extends_002_F(taint_src):
    derived = DerivedClass('_')
    derived.process()


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    constructor_extends_002_F(taint_src)

