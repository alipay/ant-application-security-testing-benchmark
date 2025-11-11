# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->可变字节序列
# scene introduction = bytearray切片操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytearray/bytearray_slice_001_T
# evaluation information end

import os

def bytearray_slice_001_T(taint_src):
    # 创建包含污点数据的bytearray
    clean_part = "clean_data_"
    ba = bytearray(clean_part + taint_src, 'utf-8')
    
    # 通过切片操作提取包含污点数据的部分
    tainted_slice = ba[len(clean_part):len(clean_part) + len(taint_src)]
    
    # 将切片结果传递给sink，期望引擎识别出污点数据
    taint_sink(tainted_slice)

def taint_sink(o):
    os.system(bytes(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytearray_slice_001_T(taint_src)