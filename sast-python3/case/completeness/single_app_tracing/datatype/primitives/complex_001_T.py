# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 复数
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/complex_001_T
# evaluation information end
import os

def complex_001_T(taint_src):
    tainted_complex = complex(taint_src,4)
    taint_sink(tainted_complex)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = 3  
    complex_001_T(taint_src)
