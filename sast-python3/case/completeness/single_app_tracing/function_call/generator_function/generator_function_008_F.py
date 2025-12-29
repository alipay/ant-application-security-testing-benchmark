# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
# scene introduction = 生成器表达式
# level = 2
# bind_url = completeness/single_app_tracing/function_call/generator_function/generator_function_008_F
# evaluation information end
import os

def generator_function_008_F(taint_src):
    # 使用生成器表达式，并将污染数据作为输出的一部分
    gen = ("_" for _ in range(1))  # 简化示例，仅循环一次以展示概念
    taint_sink(next(gen))

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"  
    generator_function_008_F(taint_src)
