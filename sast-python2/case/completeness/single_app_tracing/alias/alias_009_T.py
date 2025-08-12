# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 对象属性别名
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_009_T
# evaluation information end
import os


def alias_009_T(taint_src):
    class Obj(object):
        def __init__(self):
            self.data = u'_'

    a = Obj()
    b = a  # 别名
    b.data = taint_src
    taint_sink(a.data)  

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    alias_009_T(taint_src)

