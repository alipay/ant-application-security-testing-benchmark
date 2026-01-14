# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = 自定义带参装饰器
# level = 2
# bind_url = completeness/single_app_tracing/function_call/decorator_function/custom_decorator_with_param_002_F
# date = 2026-01-06 09:53:06
# evaluation information end
import os


def custom_decorator_with_param_002_F(taint_src):
    def my_decorator_with_param(prefix):
        def decorator(func):
            def wrapper(*args, **kwargs):
                return func((prefix), **kwargs)
            return wrapper
        return decorator

    # 场景特点：使用自定义带参装饰器修饰函数，但传递的是安全值
    @my_decorator_with_param("safe_value")
    def process(data):
        return data

    result = process(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    custom_decorator_with_param_002_F(taint_src)
