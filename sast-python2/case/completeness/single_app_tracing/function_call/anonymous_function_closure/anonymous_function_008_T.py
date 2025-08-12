# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
# scene introduction = 基本闭包
# level = 2
# bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_008_T
# evaluation information end

import os

def anonymous_function_008_T(taint_src):
    # 使用lambda创建一个闭包，该闭包能够访问外部变量taint_src
    make_adder = lambda n: (lambda x: taint_sink(x + n))
    
    # 直接调用make_adder，传入taint_src作为n，并立即执行返回的lambda，传入"additional data"作为x
    make_adder(taint_src)(u"abc")

def taint_sink(o):
    os.system(o)

if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    anonymous_function_008_T(taint_src)
