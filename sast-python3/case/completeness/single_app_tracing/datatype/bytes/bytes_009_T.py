# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->不可变字节序列
# scene introduction = 字节拼接
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytes/bytes_009_T
# evaluation information end


import os


def bytes_009_T(taint_src):
    # 创建两个字节序列并进行拼接
    byte_data1 = bytes(taint_src, 'utf-8')
    byte_data2 = b"_concatenated"
    concatenated_data = byte_data1 + byte_data2
    taint_sink(concatenated_data)  # 将拼接结果传递给 taint_sink


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytes_009_T(taint_src)
