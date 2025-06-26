# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 参数值传递->默认参数
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_006_F
# evaluation information end
import os

def argument_passing_value_006_F(taint_src):
    def process(arg="safe_value"):
        taint_sink(arg)  
    
    process()

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    argument_passing_value_006_F(taint_src)  

