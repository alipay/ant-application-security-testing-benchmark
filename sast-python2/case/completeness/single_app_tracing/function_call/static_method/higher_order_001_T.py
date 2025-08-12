# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->静态方法
# scene introduction = 静态方法为参数
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/static_method/higher_order_001_T
# evaluation information end
import os


class Calculator:
    @staticmethod
    def add_taint(a, b):
        return "{}_{}".format(a, b)  # 污点扩展


def execute_operation(op_func, a, b):
    return op_func(a, b)  # 调用传入的静态方法


def higher_order_001_T(taint_src):
    result = execute_operation(Calculator.add_taint, taint_src, "_")
    taint_sink(result)  # 输出 "10_5_#taint_src_value"


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    higher_order_001_T(taint_src)

