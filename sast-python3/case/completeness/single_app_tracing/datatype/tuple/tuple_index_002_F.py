# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->元组
# scene introduction = 元组索引访问
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/tuple/tuple_index_002_F
# evaluation information end

import os

def tuple_index_002_F(taint_src):
    # 创建包含干净数据的元组
    t = ("clean1", taint_src, "clean3")
    
    # 访问第一个元素
    first_item = t[0]
    
    # 传递给sink
    taint_sink(first_item)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    tuple_index_002_F(taint_src)