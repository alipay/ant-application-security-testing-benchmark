# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = classmethod
# level = 2
# bind_url = completeness/single_app_tracing/function_call/decorator_function/classmethod_decorator_002_F
# evaluation information end

import os

class Validator:
    @classmethod
    def validate(cls, data):
        return "_"


def classmethod_decorator_002_F(taint_src):
    result = Validator.validate(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)

if __name__ == '__main__':
    taint_src = "taint_src_value"
    classmethod_decorator_002_F(taint_src)