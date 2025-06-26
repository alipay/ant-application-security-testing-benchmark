# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
# scene introduction = 浮点型转换
# level = 2
# bind_url = completeness/single_app_tracing/expression/type_cast/float_truncate_001_T
# evaluation information end
import os


def float_truncate_001_T(taint_src):
    result = float(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = u"123"
    float_truncate_001_T(taint_src)

