# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->不可变字节序列
# scene introduction = 字面量形式
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytes/bytes_002_F
# evaluation information end


import os


def bytes_002_F(taint_src):
    # 使用字面量创建字节序列
    byte_data = b"aaa"
    taint_sink(byte_data)  # 将字节序列传递给 taint_sink


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytes_002_F(taint_src)
