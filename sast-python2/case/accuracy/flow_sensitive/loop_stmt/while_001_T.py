# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->流敏感分析->循环顺序执行语句
# scene introduction = while
# level = 2
# bind_url = accuracy/flow_sensitive/loop_stmt/while_001_T
# date = 2026-01-08 02:38:35
# evaluation information end
import os

def while_001_T(taint_src):
    i = 3
    while i > 0:
      taint_sink(taint_src)
      i = i - 1

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    while_001_T(taint_src)