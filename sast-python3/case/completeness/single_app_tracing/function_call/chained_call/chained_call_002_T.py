# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->链式调用
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/function_call/chained_call/chained_call_002_T
# evaluation information end
import os

def chained_call_002_T(taint_src):
    # 创建类 A 的实例并进行链式调用
    A(taint_sink).set_name("_").clear_name().set_name(taint_src).process()


def taint_sink(o):
    os.system(o)  


class A:
    def __init__(self, taint_sink):
        self.name = ''
        self.taint_sink = taint_sink  # 将污染接收函数保存为实例属性

    def set_name(self, name):
        self.name = name
        return self  # 返回实例以支持链式调用

    def clear_name(self):
        self.name = ''
        return self  # 返回实例以支持链式调用

    def process(self):
        self.taint_sink(self.name)  # 使用保存的污染接收函数



if __name__ == "__main__":
    taint_src = "taint_src_value"
    chained_call_002_T(taint_src)  # 调用函数，传入污染源值 taint_src

