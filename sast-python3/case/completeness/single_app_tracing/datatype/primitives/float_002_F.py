# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 浮点型
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/float_002_F
# evaluation information end
import os

def float_002_F(taint_src):
    tainted_float = taint_src
    tainted_float = 4.14
    taint_sink(tainted_float)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = 3.0
    float_002_F(taint_src)
