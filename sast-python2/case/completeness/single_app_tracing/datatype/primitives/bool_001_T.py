# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 布尔型
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/bool_001_T
# evaluation information end
import os

def bool_001_T(taint_src):
    taint_sink(taint_src)

def taint_sink(o):
    os.system(unicode(o))

if __name__ == u"__main__":
    taint_src = True
    bool_001_T(taint_src)
