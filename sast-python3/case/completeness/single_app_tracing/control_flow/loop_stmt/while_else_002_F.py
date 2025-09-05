# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = while_else
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/while_else_002_F
# evaluation information end

import os

def while_else_002_F(taint_src):
    i = 2
    res = taint_src
    while i < 2:
        i += 1
        break
    else:
        res = "_"

    taint_sink(res)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    while_else_002_F(taint_src)
