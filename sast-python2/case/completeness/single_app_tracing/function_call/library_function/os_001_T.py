# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
# scene introduction = os库
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/library_function/os_001_T
# evaluation information end

import os

def os_001_T(taint_src):
    file_path = os.path.join(u"/data", taint_src)
    taint_sink(file_path)  # 输出拼接后的路径

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value.txt"
    os_001_T(taint_src)

