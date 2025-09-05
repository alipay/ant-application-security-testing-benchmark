# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->跳转语句
# scene introduction = continue
# level = 4+
# bind_url = accuracy/path_sensitive/explicit_jump_control/continue_002_F
# evaluation information end
import os


def continue_002_F(taint_src):
    res = u''
    for i in xrange(10):
        if i == 3:
            res = taint_src
            continue
            taint_sink(res)

def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    continue_002_F(taint_src)

