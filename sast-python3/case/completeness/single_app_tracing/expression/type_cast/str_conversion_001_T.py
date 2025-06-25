# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
# scene introduction = 字符串转换
# level = 2
# bind_url = completeness/single_app_tracing/expression/type_cast/str_conversion_001_T
# evaluation information end
import os

def str_conversion_001_T(taint_src):
    tainted_str = str(taint_src)
    taint_sink(tainted_str)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = 123
    str_conversion_001_T(taint_src)
