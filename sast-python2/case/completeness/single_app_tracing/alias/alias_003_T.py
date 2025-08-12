# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 多级别名链
# level = 2+
# bind_url = completeness/single_app_tracing/alias/alias_003_T
# evaluation information end
import os


def alias_003_T(taint_src):
    a = {u'value': u'_'}
    b = a  # 别名链1
    c = b  # 别名链2
    c[u'value'] = taint_src  # 修改末级别名
    taint_sink(a[u'value'])  # 应被污染

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    alias_003_T(taint_src)

