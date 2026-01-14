# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 长整型
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/long_002_F
# date = 2026-01-06 02:34:28
# evaluation information end
import os


def long_002_F(taint_src):
    # 场景特点：长整型数据被安全值替换
    tainted_long = taint_src
    safe_long = 456L
    taint_sink(safe_long)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u"__main__":
    taint_src = 123
    long_002_F(taint_src)
