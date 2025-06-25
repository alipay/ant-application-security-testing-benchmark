# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
# scene introduction = yield_from
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/generator_function/yieldFrom_004_F
# evaluation information end

import os

def yieldFrom_004_F(taint_src):
    def generator():
        yield from (x for x in "aaa")  # 使用 yield from 嵌套生成器
    result = "".join(generator())
    taint_sink(result)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    yieldFrom_004_F(taint_src)
