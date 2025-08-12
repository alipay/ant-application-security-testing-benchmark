# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 参数值传递->默认参数
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_005_T
# evaluation information end
import os

def argument_passing_value_005_T(taint_src):
    def process(arg=u"safe_value"):
        taint_sink(arg)  # 显式参数应覆盖默认值
    
    process(taint_src)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    argument_passing_value_005_T(taint_src)  # 输出: taint_src_value


