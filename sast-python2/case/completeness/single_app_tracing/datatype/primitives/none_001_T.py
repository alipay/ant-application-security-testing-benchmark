# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = NoneType
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/none_001_T
# evaluation information end
import os

def none_001_T(taint_src):
    tainted_none = taint_src
    taint_sink(tainted_none)

def taint_sink(o):
    os.system(unicode(o))

if __name__ == u"__main__":
    taint_src = None
    none_001_T(taint_src)
