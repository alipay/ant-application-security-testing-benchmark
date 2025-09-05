# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 函数参数别名
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_007_T
# evaluation information end
import os


def alias_007_T(taint_src):
    def modify_param(obj):
        obj[u'value'] = taint_src  # 通过参数修改

    a = {u'value': u'_'}
    modify_param(a)  # 传递别名
    taint_sink(a[u'value'])  # 应被污染

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    alias_007_T(taint_src)

