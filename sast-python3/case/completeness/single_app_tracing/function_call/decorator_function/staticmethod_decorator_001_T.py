# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = staticmethod
# level = 2
# bind_url = completeness/single_app_tracing/function_call/decorator_function/staticmethod_decorator_001_T
# evaluation information end

import os

class Processor:
    # 没有 self 或 cls 参数	不会自动传入实例或类，所以不能访问类或实例属性。只接受传递进来的参数。
    @staticmethod
    def process(data):
        return data


def staticmethod_decorator_001_T(taint_src):
    # 不需要创建类的实例即可调用静态方法。
    result = Processor.process(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)

if __name__ == '__main__':
    taint_src = "taint_src_value"
    staticmethod_decorator_001_T(taint_src)