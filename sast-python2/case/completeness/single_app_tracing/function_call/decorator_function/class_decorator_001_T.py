# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = 类装饰器
# level = 2
# bind_url = completeness/single_app_tracing/function_call/decorator_function/class_decorator_001_T
# date = 2026-01-06 09:53:06
# evaluation information end
import os


def class_decorator_001_T(taint_src):
    class MyDecorator(object):
        def __init__(self, cls):
            self.cls = cls

        def __call__(self, *args, **kwargs):
            return self.cls((taint_src), **kwargs)

    # 场景特点：使用类装饰器修饰类
    @MyDecorator
    class MyClass(object):
        def __init__(self, data):
            self.data = data

        def get_data(self):
            return self.data

    obj = MyClass("safe_value")
    taint_sink(obj.get_data())


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    class_decorator_001_T(taint_src)
