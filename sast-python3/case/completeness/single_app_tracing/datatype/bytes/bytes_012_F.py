# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->不可变字节序列
# scene introduction = 解码
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytes/bytes_012_F
# evaluation information end


import os


def bytes_012_F(taint_src):
    # 创建字节序列并解码为字符串
    byte_data = bytes("aaa", 'utf-8')
    decoded_data = byte_data.decode('utf-8')
    taint_sink(decoded_data)  # 将解码后的字符串传递给 taint_sink


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytes_012_F(taint_src)
