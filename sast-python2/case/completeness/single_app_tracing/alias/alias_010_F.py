# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 对象属性别名
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_010_F
# evaluation information end
import os


def alias_010_F(taint_src):
    class Obj(object):
        def __init__(self):
            self.data = taint_src 

    a = Obj()
    b = a  # 别名
    b.data = u'_'
    taint_sink(a.data)  

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    alias_010_F(taint_src)

