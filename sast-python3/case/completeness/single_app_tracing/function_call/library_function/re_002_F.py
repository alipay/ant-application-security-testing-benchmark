# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
# scene introduction = re库
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/library_function/re_002_F
# evaluation information end

import os
import re

def re_002_F(taint_src):
    pattern = r'[a-zA-Z]+'
    words = re.findall(pattern, "aaa,bbb,ccc")
    taint_sink(words)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    re_002_F(taint_src)

