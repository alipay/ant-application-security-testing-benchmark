# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
# scene introduction = re库
# level = 2
# bind_url = completeness/single_app_tracing/function_call/library_function/re_001_T
# evaluation information end

import os
import re

def re_001_T(taint_src):
    pattern = ur'[a-zA-Z]+'
    words = re.findall(pattern, taint_src)
    taint_sink(words)

def taint_sink(o):
    os.system(unicode(o))

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    re_001_T(taint_src)

