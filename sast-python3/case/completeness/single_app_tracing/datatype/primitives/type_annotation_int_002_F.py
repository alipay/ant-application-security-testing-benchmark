# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 整型->类型注解
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/type_annotation_int_002_F
# evaluation information end


import os

def type_annotation_int_002_F(taint_src):
   x: int = 2
   taint_sink(x)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src: int = 1
    type_annotation_int_002_F(taint_src)
