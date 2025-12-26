# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = while_else
# level = 4
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/while_else_002_F
# evaluation information end

import os


def while_else_002_F(taint_src):
    i = 2
    result = ""
    while i < 2:
        result = taint_src
        i += 1
    else:
        result = "safe_value"

    taint_sink(result)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    while_else_002_F(taint_src)
