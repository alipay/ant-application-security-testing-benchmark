# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->不可变字节序列
# scene introduction = 索引访问
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytes/bytes_006_F
# evaluation information end


import os


def bytes_006_F(taint_src):
    # 创建字节序列并进行索引访问
    byte_data = bytes("aaa", 'utf-8')
    first_byte = byte_data[0]
    taint_sink(first_byte)  # 将第一个字节传递给 taint_sink


def taint_sink(o):
    os.system(str(o))


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytes_006_F(taint_src)
