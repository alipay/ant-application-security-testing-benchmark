# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->流敏感分析->循环顺序执行语句
# scene introduction = for_enumerate
# level = 2+
# bind_url = accuracy/flow_sensitive/loop_stmt/for_enumerate_002_F
# evaluation information end

import os

def for_enumerate_002_F(taint_src):
    for index, value in enumerate(['b','a']):
        taint_sink("{}: {}".format(value, index))

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    for_enumerate_002_F(taint_src)
