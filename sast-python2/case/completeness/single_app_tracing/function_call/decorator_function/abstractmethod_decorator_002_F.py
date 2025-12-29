# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = abstractmethod
# level = 2
# bind_url = completeness/single_app_tracing/function_call/decorator_function/abstractmethod_decorator_002_F
# evaluation information end

import os
from abc import ABCMeta, abstractmethod

class BaseProcessor(object):
    __metaclass__ = ABCMeta  

    @abstractmethod
    def process(self, data):
        pass


class ConcreteProcessor(BaseProcessor):
    def process(self, data):
        return data


def abstractmethod_decorator_002_F(taint_src):
    processor = ConcreteProcessor()
    result = processor.process("_")
    taint_sink(result)


def taint_sink(o):
    os.system(o)

if __name__ == '__main__':
    taint_src = "taint_src_value"
    abstractmethod_decorator_002_F(taint_src)