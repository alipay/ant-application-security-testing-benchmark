# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->流敏感分析->循环顺序执行语句
# scene introduction = for_zip
# level = 2
# bind_url = accuracy/flow_sensitive/loop_stmt/for_zip_001_T
# evaluation information end

import os

def for_zip_001_T(taint_src):
    for a, b in zip([taint_src, 2], [3, 4]):
        taint_sink(f"{a} and {b}")

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    for_zip_001_T(taint_src)
