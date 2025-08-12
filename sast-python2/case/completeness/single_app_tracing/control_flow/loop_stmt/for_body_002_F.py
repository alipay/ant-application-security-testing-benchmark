# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = for_body
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_body_002_F
# evaluation information end
import os


def for_body_002_F(taint_src):
    res = taint_src
    for i in xrange(2):
        res = u'_'
    taint_sink(res)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    for_body_002_F(taint_src)

