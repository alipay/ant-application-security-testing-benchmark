# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->Lambda表达式
# scene introduction = lambda关键字
# level = 2
# bind_url = completeness/single_app_tracing/expression/lambda_expression/lambda_expression_003_T
# evaluation information end
import os


def lambda_expression_003_T(taint_src):
    # 初始化结果变量
    result = ''
    
    # 使用 lambda 创建一个匿名函数，该函数接收一个参数并返回相同的参数
    lambda_func = lambda a: a
    
    # 调用 lambda 函数并将结果赋值给 result 变量
    result = lambda_func(taint_src)
    
    # 将结果传递给污染接收函数
    taint_sink(result)

def taint_sink(o):
    os.system(o)  

if __name__ == "__main__":
    taint_src = "taint_src_value"
    lambda_expression_003_T(taint_src)  # 调用函数，传入污染源值 taint_src

