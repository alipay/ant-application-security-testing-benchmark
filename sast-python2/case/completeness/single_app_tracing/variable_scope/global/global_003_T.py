# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->全局变量
# scene introduction = 多个全局变量
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/global/global_003_T
# evaluation information end


import os

def global_003_T(taint_src):
    global x, y  # 使用 global 声明多个全局变量
    x = taint_src
    y = u"suffix"
    taint_sink(x + y)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    global_003_T(taint_src)

