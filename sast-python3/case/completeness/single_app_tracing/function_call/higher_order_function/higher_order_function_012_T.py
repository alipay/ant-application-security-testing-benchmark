# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
# scene introduction = 回调函数类型注解
# level = 2
# bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_012_T
# evaluation information end
import os
#ParamSpec 的主要设计目的是与 Callable 一起使用，因为它用于捕获函数的参数类型信息。
#通常情况下，ParamSpec 会与 Callable 结合使用，以表示函数的参数和返回值类型
from typing import Callable, ParamSpec, TypeVar

# 定义 ParamSpec 和 TypeVar
P = ParamSpec('P')  # 表示回调函数的参数类型
R = TypeVar('R')    # 表示回调函数的返回值类型

def higher_order_function_012_T(taint_src):
    # 定义一个高阶函数，接受一个回调函数和任意参数
    def process_callback(callback: Callable[P, R], *args: P.args, **kwargs: P.kwargs) -> R:
        return callback(*args, **kwargs)

    # 实现一个符合签名的回调函数
    def to_upper(s: str) -> str:
        return s.upper()

    # 使用高阶函数处理回调函数
    result = process_callback(to_upper, taint_src)
    taint_sink(result)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    higher_order_function_012_T(taint_src)

