# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->多次调用
# scene introduction = 嵌套函数调用
# level = 2
# bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_004_F
# evaluation information end
import os

def multi_invoke_004_F(taint_src):
    def process1(arg):
        return arg
    
    def process2(arg):
        return '_'  # 拦截污染值
    
    result = process2(process1(taint_src))  # 污染值被拦截
    taint_sink(result)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    multi_invoke_004_F(taint_src)

