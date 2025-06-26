# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->不可变字节序列
# scene introduction = 切片操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/bytes/bytes_007_T
# evaluation information end


import os

# 属于域敏感
def bytes_007_T(taint_src):
    # 创建字节序列并进行切片操作
    byte_data = bytes(taint_src, 'utf-8')
    sliced_data = byte_data[1:5]
    taint_sink(sliced_data)  # 将切片结果传递给 taint_sink


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytes_007_T(taint_src)
