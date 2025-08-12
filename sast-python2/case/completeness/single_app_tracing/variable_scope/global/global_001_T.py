# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->全局变量
# scene introduction = 单个全局变量
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/global/global_001_T
# evaluation information end


import os

def global_001_T(taint_src):
    global x  # 使用 global 声明单个全局变量
    x = taint_src
    taint_sink(x)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    global_001_T(taint_src)
