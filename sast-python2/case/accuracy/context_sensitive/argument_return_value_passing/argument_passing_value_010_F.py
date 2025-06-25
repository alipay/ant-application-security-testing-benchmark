# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 参数值传递->可变参数
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_010_F
# evaluation information end
import os

def argument_passing_value_010_F(taint_src):
    def wrapper(*args):
        taint_sink(args[1])  
    
    wrapper(taint_src, u"safe_value")  # 污染源在第一个位置但未被使用

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    argument_passing_value_010_F(taint_src)  # 输出: safe_value


