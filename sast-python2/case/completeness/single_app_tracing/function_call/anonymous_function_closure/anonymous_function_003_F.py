# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
# scene introduction = 参数传递
# level = 2
# bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_003_F
# evaluation information end

import os

def anonymous_function_003_F(taint_src):
    process = lambda x, y: taint_sink(u"_" + y)
    process(taint_src, u"_")

def taint_sink(o):
    os.system(o)

if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    anonymous_function_003_F(taint_src)
