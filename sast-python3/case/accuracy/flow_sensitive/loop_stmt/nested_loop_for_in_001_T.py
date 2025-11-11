# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->流敏感分析->循环语句
# scene introduction = 循环语句->嵌套循环
# level = 2+
# bind_url = accuracy/flow_sensitive/loop_stmt/nested_loop_for_in_001_T
# evaluation information end
import os


def nested_loop_for_in_001_T(taint_src):
    # 嵌套循环中的污点传播
    for outer in [taint_src]:
        for inner in ["safe"]:
            taint_sink(outer)  # 外层循环变量（污染数据）


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    nested_loop_for_in_001_T(taint_src)
