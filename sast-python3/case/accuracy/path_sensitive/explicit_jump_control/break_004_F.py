# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->跳转语句
# scene introduction = break-嵌套循环
# level = 4+
# bind_url = accuracy/path_sensitive/explicit_jump_control/break_003_F
# evaluation information end
import os


def break_003_F(taint_src):
    res = ""
    for i in range(2):
        for j in range(2):
            if i == 1 and j == 1:
                res = "clean"
                break  # 跳出内层循环，但外层继续
        taint_sink(res)  # 外层未污染的sink调用


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    break_003_F(taint_src)

