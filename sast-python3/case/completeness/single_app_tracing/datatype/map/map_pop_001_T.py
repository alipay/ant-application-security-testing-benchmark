# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
# scene introduction = pop操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/map/map_pop_001_T
# evaluation information end

import os

def map_pop_001_T(taint_src):
    # 创建包含污点数据的字典
    m = {"key1": taint_src, "key2": "clean"}
    
    # 弹出指定键的值（污点值）
    popped_value = m.pop("key1")
    # 将弹出的值传递给sink
    taint_sink(popped_value)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_pop_001_T(taint_src)