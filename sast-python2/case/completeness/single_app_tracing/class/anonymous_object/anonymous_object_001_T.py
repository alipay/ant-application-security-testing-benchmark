# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->匿名对象
# scene introduction = 匿名对象
# level = 2
# bind_url = completeness/single_app_tracing/class/anonymous_object/anonymous_object_001_T
# evaluation information end
import os


def anonymous_object_001_T(taint_src):
    obj = {"data": taint_src}
    taint_sink(obj["data"])


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    anonymous_object_001_T(taint_src)
