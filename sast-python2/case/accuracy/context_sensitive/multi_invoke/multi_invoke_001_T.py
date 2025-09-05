# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->多次调用
# scene introduction = 两次调用传入不同参数
# level = 2
# bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_001_T
# evaluation information end
import os

def multi_invoke_001_T(taint_src):
    def process(arg):
        return arg
    
    a = process(taint_src)
    b = process(u'_')
    taint_sink(a)

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    multi_invoke_001_T(taint_src)

