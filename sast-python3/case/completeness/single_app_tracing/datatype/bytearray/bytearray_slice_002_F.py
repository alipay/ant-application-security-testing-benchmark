# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->可变字节序列
# scene introduction = bytearray切片操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytearray/bytearray_slice_002_F
# evaluation information end

import os

def bytearray_slice_002_F(taint_src):
    # 创建只包含干净数据的bytearray
    clean_data = "clean_data_only"
    ba = bytearray(clean_data, 'utf-8')
    
    # 通过切片操作提取部分数据
    clean_slice = ba[0:5]  # 提取"clean"
    
    # 将切片结果传递给sink，期望引擎不识别为污点数据
    taint_sink(clean_slice)

def taint_sink(o):
    os.system(bytes(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytearray_slice_002_F(taint_src)