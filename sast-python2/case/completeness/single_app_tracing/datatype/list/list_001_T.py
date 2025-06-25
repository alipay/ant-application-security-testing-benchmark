# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_001_T
# evaluation information end
import os


def list_001_T(taint_src):
    s = [taint_src, u'b', u'c']
    taint_sink(s)


def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    list_001_T(taint_src)

