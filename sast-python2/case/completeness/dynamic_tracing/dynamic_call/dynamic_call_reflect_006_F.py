# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->动态特性跟踪完整度->反射调用
# scene introduction = 字符串常量->反射
# level = 3
# bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_006_F
# evaluation information end
import os


def dynamic_call_reflect_006_F(taint_src):
    class Obj:
        def __init__(self,value):
            self.data = value

    obj = Obj(taint_src)

    delattr(obj, u'data')
    setattr(obj, u'data', u"aa")
    taint_sink(getattr(obj, u'data'))


def taint_sink(o):
    os.system(o)

if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    dynamic_call_reflect_006_F(taint_src)

