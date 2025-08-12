# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
# scene introduction = 
# level = 2+
# bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_002_F
# evaluation information end
import os


def static_variable_002_F(taint_src):
    class A(object):
        data = u'_'

    taint_sink(A.data)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    static_variable_002_F(taint_src)

