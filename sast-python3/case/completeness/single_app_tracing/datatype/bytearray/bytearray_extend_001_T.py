# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->可变字节序列
# scene introduction = bytearray扩展操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytearray/bytearray_extend_001_T
# evaluation information end

import os

def bytearray_extend_001_T(taint_src):
    # 创建初始的干净bytearray
    ba = bytearray("clean_data", 'utf-8')
    
    # 创建包含污点数据的bytes
    tainted_bytes = bytearray(taint_src, 'utf-8')
    
    # 使用extend操作扩展污点数据
    ba.extend(tainted_bytes)
    
    # 将扩展后的bytearray传递给sink，期望引擎识别出污点数据
    taint_sink(ba)

def taint_sink(o):
    os.system(bytes(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytearray_extend_001_T(taint_src)