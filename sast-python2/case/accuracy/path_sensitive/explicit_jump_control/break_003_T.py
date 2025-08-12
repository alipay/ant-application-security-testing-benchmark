# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->跳转语句
# scene introduction = break-嵌套循环
# level = 4+
# bind_url = accuracy/path_sensitive/explicit_jump_control/break_003_T
# evaluation information end
import os


def break_003_T(taint_src):
    res = u""
    for i in xrange(2):
        for j in xrange(2):
            if i == 1 and j == 0:
                res = taint_src
                break  # 跳出内层循环，但外层循环继续
        taint_sink(res)  # 外层循环污染后的sink调用


def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    break_003_T(taint_src)

