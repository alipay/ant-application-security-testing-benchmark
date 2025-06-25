# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->链式调用
# scene introduction = 函数组合
# level = 2
# bind_url = completeness/single_app_tracing/function_call/chained_call/chained_call_006_F
# evaluation information end
import os


def chained_call_006_F(taint_src):
    def add(x):
        return x

    def multiply(f):
        return lambda: "_"

    # 组合函数形成链式
    result = multiply(add(taint_src))()  
    taint_sink(result)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    chained_call_006_F(taint_src)  

