# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = 嵌套循环
# level = 2+
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/nested_loop_002_F
# evaluation information end

import os

def nested_loop_002_F(taint_src):
    res = u'_'
    for i in xrange(2):
        for j in xrange(2):
            break
            res += taint_src
    taint_sink(res)
               

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    nested_loop_002_F(taint_src)

