# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->可变字节序列
# scene introduction = 构造函数形式
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytearray/bytearray_001_T
# evaluation information end


import os


def bytearray_001_T(taint_src):
    # 使用 bytearray() 构造函数创建可变字节序列
    mutable_byte_data = bytearray(taint_src, 'utf-8')
    mutable_byte_data[0] = 65  # 修改第一个字节为 ASCII 码为 65 的字符 'A'
    taint_sink(mutable_byte_data)  # 将修改后的字节序列传递给 taint_sink


def taint_sink(o):
    os.system(bytes(o))


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytearray_001_T(taint_src)
