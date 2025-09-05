# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
# scene introduction = 字典/映射（Map）对象
# level = 2
# bind_url = completeness/single_app_tracing/datatype/map/map_001_T
# evaluation information end
import os


def map_001_T(taint_src):
    m = dict()  # 或者直接使用 m = {}
    m[u'key1'] = taint_src
    taint_sink(m)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_001_T(taint_src)

