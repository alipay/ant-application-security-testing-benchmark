# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 返回值传递->多层函数嵌套传递
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_003_T
# evaluation information end
import os

def return_value_passing_003_T(taint_src):
    def inner1():
        return taint_src
    
    def inner2():
        return inner1()
    
    data = inner2()
    taint_sink(data)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    return_value_passing_003_T(taint_src)

