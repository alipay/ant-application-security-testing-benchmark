# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->Lambda表达式
# scene introduction = 多参数lambda表达式
# level = 2
# bind_url = completeness/single_app_tracing/expression/lambda_expression/lambda_multi_params_001_T
# evaluation information end

import os

def lambda_multi_params_001_T(taint_src):
    # 直接使用污点数据
    tainted_data = taint_src
    
    # 创建多参数lambda函数
    lambda_func = lambda x, y, z: x + y + z
    
    # 调用lambda函数，第一个参数是污点数据
    result = lambda_func(tainted_data, "_clean", "_clean")
    
    # 传递给sink
    taint_sink(result)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    lambda_multi_params_001_T(taint_src)