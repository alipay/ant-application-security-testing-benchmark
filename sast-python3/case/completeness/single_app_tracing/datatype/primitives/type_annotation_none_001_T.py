# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = NoneType->类型注解
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/type_annotation_none_001_T
# evaluation information end


import os

def type_annotation_none_001_T(taint_src):
    def greet() -> None:
        pass

    x: None  = greet()
    taint_sink(taint_src)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    type_annotation_none_001_T(taint_src)
