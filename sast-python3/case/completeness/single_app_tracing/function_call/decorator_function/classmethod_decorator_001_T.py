# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = classmethod
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/decorator_function/classmethod_decorator_001_T
# evaluation information end

import os

class Validator:
    # 使用 @classmethod 装饰器，将下面的方法标记为 类方法。 那么类方法就可以访问类属性，也可以通过类名直接调用。
    # cls为类本身 而不是代表实例self，所以不能访问实例属性，data为传递的参数
    @classmethod
    def validate(cls, data):
        return data


def classmethod_decorator_001_T(taint_src):
    # 不需要创建类的实例即可调用标记为的类方法。
    # 否则 只能result = Validator()创建了类实例后，才能result.validate(taint_src)
    result = Validator.validate(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)

if __name__ == '__main__':
    taint_src = "taint_src_value"
    classmethod_decorator_001_T(taint_src)