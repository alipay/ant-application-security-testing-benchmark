# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 别名问题
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_002_F
# evaluation information end
import os


def alias_002_F(taint_src):
    a = {u'value':taint_src }
    b = a
    b[u'value'] = u'_'
    taint_sink(a[u'value'])


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    alias_002_F(taint_src)

