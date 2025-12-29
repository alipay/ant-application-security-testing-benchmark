# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = abstractmethod
# level = 2
# bind_url = completeness/single_app_tracing/function_call/decorator_function/abstractmethod_decorator_001_T
# evaluation information end

import os
from abc import ABC, abstractmethod

class BaseProcessor(ABC):
    # 使用 @abstractmethod 可以让你定义一种“模板”----告诉所有子类：“你必须实现这些方法”。
    # 在基类中通过@abstractmethod绑定了一个方法process，那么所有子类中都必须声明且重写process方法
    @abstractmethod
    def process(self, data):
        pass


class ConcreteProcessor(BaseProcessor):
    def process(self, data):
        return data



def abstractmethod_decorator_001_T(taint_src):
    processor = ConcreteProcessor()
    result = processor.process(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)

if __name__ == '__main__':
    taint_src = "taint_src_value"
    abstractmethod_decorator_001_T(taint_src)