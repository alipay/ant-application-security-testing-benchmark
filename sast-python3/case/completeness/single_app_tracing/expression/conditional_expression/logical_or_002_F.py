# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->条件表达式
# scene introduction = 逻辑或
# level = 2
# bind_url = completeness/single_app_tracing/expression/conditional_expression/logical_or_002_F
# evaluation information end
import os


def logical_or_002_F(taint_src):
     result = taint_src == "taint_src" or taint_src.endswith("_") # 污点通过OR条件传递
     taint_sink(result) 

def taint_sink(o):
    os.system(str(o))


if __name__ == "__main__":
    taint_src = "taint_src_value"
    logical_or_002_F(taint_src)

