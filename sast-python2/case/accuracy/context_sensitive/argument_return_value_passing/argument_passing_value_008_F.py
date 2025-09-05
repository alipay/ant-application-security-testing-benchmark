# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 参数值传递->关键字参数
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_008_F
# evaluation information end
import os

def argument_passing_value_008_F(taint_src):
    def process(safe_arg, tainted_arg):
        taint_sink(safe_arg)  
    
    process(safe_arg=u"_", tainted_arg=taint_src)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    argument_passing_value_008_F(taint_src)  # 输出: _


