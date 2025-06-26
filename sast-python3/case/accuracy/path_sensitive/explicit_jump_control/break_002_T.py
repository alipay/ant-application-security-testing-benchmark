# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->跳转语句
# scene introduction = break
# level = 4+
# bind_url = accuracy/path_sensitive/explicit_jump_control/break_002_T
# evaluation information end
import os


def break_002_T(taint_src):
    res = ""
    for i in range(10):
        if i == 3:
            res = taint_src
        taint_sink(res)


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    break_002_T(taint_src)

