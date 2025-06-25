# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 整型
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/int_002_F
# evaluation information end
import os

def int_002_F(taint_src):
    tainted_int = taint_src
    tainted_int = 678
    taint_sink(tainted_int)

def taint_sink(o):
    os.system(str(o))

# 示例调用
if __name__ == "__main__":
    taint_src = 123  
    int_002_F(taint_src)
