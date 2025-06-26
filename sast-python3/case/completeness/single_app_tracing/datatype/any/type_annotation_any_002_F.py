# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->any
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/datatype/any/type_annotation_any_002_F
# evaluation information end

import os
from typing import Any

def type_annotation_any_002_F(taint_src):
    a: str = taint_src
    b: int = 2
    c: None = None
    d: Any #any类型相当于关闭检查 它可以被任何类型赋值
    d = c
    d = b
    taint_sink(d)

def taint_sink(o):
    os.system(str(o))

    
if __name__ == "__main__":
    taint_src = "taint_src_value"
    type_annotation_any_002_F(taint_src)
