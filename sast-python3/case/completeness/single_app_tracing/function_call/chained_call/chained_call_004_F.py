# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->链式调用
# scene introduction = 闭包链式
# level = 2
# bind_url = completeness/single_app_tracing/function_call/chained_call/chained_call_004_F
# evaluation information end
import os


def chained_call_004_F(taint_src):

    def add():
        return {
            'multiply': multiply,
        }

    def multiply():
        return {
            'process': process
        }

    def process():
        taint_sink('_')

    # 开始链式调用
    add()['multiply']()['process']()


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    chained_call_004_F(taint_src)  # 输出最终结果

