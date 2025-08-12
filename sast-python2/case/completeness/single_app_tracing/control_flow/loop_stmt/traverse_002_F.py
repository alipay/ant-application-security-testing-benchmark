# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = 遍历
# level = 2+
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/traverse_002_F
# evaluation information end

import os


def traverse_002_F(taint_src):
     arr = [u'a', u'b', u"c"]
     list(map(taint_sink, arr))


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    traverse_002_F(taint_src)

