# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
# scene introduction = 多层回调
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_009_F
# evaluation information end
import os


def higher_order_function_009_F(taint_src):
    
    def process_sequence(func1, func2):
        return lambda s: func2(func1(s))

    def add_prefix(s):
        return f""

    def add_suffix(s):
        return f"{s}post"

    combined = process_sequence(add_prefix, add_suffix)(taint_src)
    taint_sink(combined)  # 输出 "pre___TAINT_SRC_VALUE_post"


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    higher_order_function_009_F(taint_src)

