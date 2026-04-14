# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = 装饰器链
# level = 2
# bind_url = completeness/single_app_tracing/function_call/decorator_function/decorator_chain_002_F
# date = 2026-01-06 09:53:06
# evaluation information end
import os


def decorator_chain_002_F(taint_src):
    def decorator1(func):
        def wrapper(*args, **kwargs):
            return func(("safe_value"), **kwargs)
        return wrapper

    def decorator2(func):
        def wrapper(*args, **kwargs):
            result = func(*args, **kwargs)
            return result
        return wrapper

    # 场景特点：装饰器按顺序链式应用，但传递的是安全值
    @decorator1
    @decorator2
    def process(data):
        return data

    result = process(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    decorator_chain_002_F(taint_src)
