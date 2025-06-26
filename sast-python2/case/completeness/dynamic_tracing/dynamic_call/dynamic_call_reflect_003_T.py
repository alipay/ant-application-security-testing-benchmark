# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->动态特性跟踪完整度->反射调用
# scene introduction = 字符串常量->反射
# level = 3
# bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_003_T
# evaluation information end
import os


def dynamic_call_reflect_003_T(taint_src):
    class Obj(object):
        def __init__(self):
            self.data = u'_'

    obj = Obj()
    setattr(obj, u'foo', taint_src)
    taint_sink(getattr(obj, u'foo'))


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    dynamic_call_reflect_003_T(taint_src)

