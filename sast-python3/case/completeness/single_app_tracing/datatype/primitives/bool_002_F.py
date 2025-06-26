# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 布尔型
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/bool_002_F
# evaluation information end
import os

def bool_002_F(taint_src):
    taint_sink(taint_src)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = False
    bool_002_F(taint_src)
