# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 参数值传递->普通
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_002_F
# evaluation information end
import os

def argument_passing_value_002_F(taint_src):
    process(taint_src)

def process(arg):
    arg = u'_'
    taint_sink(arg)

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value" 
    argument_passing_value_002_F(taint_src)

