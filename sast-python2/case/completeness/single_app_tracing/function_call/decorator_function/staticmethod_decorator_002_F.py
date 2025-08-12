# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = staticmethod
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/decorator_function/staticmethod_decorator_002_F
# evaluation information end

import os

class Processor:
    @staticmethod
    def process(data):
        return data


def staticmethod_decorator_002_F(taint_src):
    result = Processor.process("_")
    taint_sink(result)


def taint_sink(o):
    os.system(o)

if __name__ == '__main__':
    taint_src = "taint_src_value"
    staticmethod_decorator_002_F(taint_src)