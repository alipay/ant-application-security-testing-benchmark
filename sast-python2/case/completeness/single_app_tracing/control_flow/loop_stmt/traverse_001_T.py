# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = 遍历
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/traverse_001_T
# evaluation information end

import os


def traverse_001_T(taint_src):
    arr = [u'a', u'b', taint_src]
    list(map(taint_sink, arr))


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    traverse_001_T(taint_src)

