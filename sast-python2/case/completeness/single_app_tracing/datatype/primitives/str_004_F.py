# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 字符串->字节字符串
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/str_004_F
# evaluation information end

import os

def str_004_F(taint_src):
    tainted_str = b"aaa"
    taint_sink(tainted_str)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = b"taint_src_value"
    str_004_F(taint_src)

