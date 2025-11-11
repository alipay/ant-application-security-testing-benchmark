# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->流敏感分析->循环语句
# scene introduction = 循环语句->while循环
# level = 2+
# bind_url = accuracy/flow_sensitive/loop_stmt/while_loop_002_F
# evaluation information end
import os


def while_loop_002_F(taint_src):
    # while循环中的安全数据处理
    i = 0
    while i < 1:
        taint_sink("safe_data")  # 循环体内的安全数据
        i += 1


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    while_loop_002_F(taint_src)
