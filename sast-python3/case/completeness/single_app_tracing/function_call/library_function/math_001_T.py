# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
# scene introduction = math库
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/library_function/math_001_T
# evaluation information end

import os
import math  # 导入Python标准库中的math模块，提供数学运算功能

def math_001_T(taint_src):
    # 定义内部函数process，用于处理传入的数值字符串并计算其平方根
    def process(num):
        result = math.sqrt(num)  # 使用math模块的sqrt函数计算num的平方根
        taint_sink(result) 

    process(taint_src)  


def taint_sink(o):
    os.system(str(o))  


if __name__ == "__main__":
    taint_src = 100
    math_001_T(taint_src)  

