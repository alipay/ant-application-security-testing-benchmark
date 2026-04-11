# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 长整型
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/long_001_T
# date = 2026-01-06 02:34:28
# evaluation information end
import os


def long_001_T(taint_src):
    # 场景特点：长整型数据直接传递
    tainted_long = taint_src
    taint_sink(tainted_long)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u"__main__":
    taint_src = 123L
    long_001_T(taint_src)
