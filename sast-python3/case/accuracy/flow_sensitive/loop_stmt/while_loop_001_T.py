# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->流敏感分析->循环语句
# scene introduction = 循环语句->while循环
# level = 2+
# bind_url = accuracy/flow_sensitive/loop_stmt/while_loop_001_T
# evaluation information end
import os


def while_loop_001_T(taint_src):
    # while循环中的污点传播
    i = 0
    while i < 1:
        taint_sink(taint_src)  # 循环体内的污点
        i += 1


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    while_loop_001_T(taint_src)
