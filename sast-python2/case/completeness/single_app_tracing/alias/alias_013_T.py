# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 模块别名
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_013_T
# evaluation information end
import os
import copy as my_copy


def alias_013_T(taint_src):
    s = my_copy.copy(taint_src)
    
    taint_sink(s)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    alias_013_T(taint_src)

