# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->元组
# scene introduction = 元组连接操作
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/tuple/tuple_concat_001_T
# evaluation information end

import os

def tuple_concat_001_T(taint_src):
    # 直接使用污点数据
    tainted_data = taint_src
    
    # 创建包含污点数据的元组
    t1 = (tainted_data,)
    
    # 创建干净的元组
    t2 = ("clean1", "clean2")
    
    # 执行连接操作
    result = t1 + t2
    
    # 传递给sink
    taint_sink(result)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    tuple_concat_001_T(taint_src)