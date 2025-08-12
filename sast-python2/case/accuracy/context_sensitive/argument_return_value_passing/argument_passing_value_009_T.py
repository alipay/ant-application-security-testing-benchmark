# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 参数值传递->可变参数
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_009_T
# evaluation information end
import os

def argument_passing_value_009_T(taint_src):
    def wrapper(*args):
        taint_sink(args[0])  # 可变参数首元素识别
    
    wrapper(taint_src, u"safe_value")

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    argument_passing_value_009_T(taint_src)

