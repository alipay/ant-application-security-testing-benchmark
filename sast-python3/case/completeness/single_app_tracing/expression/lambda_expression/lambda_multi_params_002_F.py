# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->Lambda表达式
# scene introduction = 多参数lambda表达式
# level = 2
# bind_url = completeness/single_app_tracing/expression/lambda_expression/lambda_multi_params_002_F
# evaluation information end

import os

def lambda_multi_params_002_F(taint_src):
    # 创建多参数lambda函数
    lambda_func = lambda x, y, z: x + y + z
    
    # 调用lambda函数，两个参数都是干净数据
    result = lambda_func("clean1", "clean2", "clean3")
    
    # 传递给sink函数，一个参数是污点数据
    taint_sink(result)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    lambda_multi_params_002_F(taint_src)