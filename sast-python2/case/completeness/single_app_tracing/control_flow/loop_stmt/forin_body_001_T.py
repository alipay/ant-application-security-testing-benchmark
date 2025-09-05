# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = forin_body
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/forin_body_001_T
# evaluation information end
import os


def forin_body_001_T(taint_src):
    obj = {u'key1': u'value1', u'key2': taint_src}
    res = u''

    for key in obj:
        res += obj[key]

    taint_sink(res)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    forin_body_001_T(taint_src)

