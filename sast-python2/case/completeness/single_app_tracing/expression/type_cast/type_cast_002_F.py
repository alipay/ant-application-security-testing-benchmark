# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
# scene introduction = 隐式类型转换
# level = 2
# bind_url = completeness/single_app_tracing/expression/type_cast/type_cast_002_F
# evaluation information end
import os


def type_cast_002_F(taint_src):
    result = taint_src == u"5"
    taint_sink(result)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = 5
    type_cast_002_F(taint_src)

