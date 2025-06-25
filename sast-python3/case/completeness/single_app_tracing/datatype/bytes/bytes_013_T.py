# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->不可变字节序列
# scene introduction = 查找
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/bytes/bytes_013_T
# evaluation information end


import os


def bytes_013_T(taint_src):
    # 创建字节序列并查找子串位置
    byte_data = bytes(taint_src, 'utf-8')
    position = byte_data.find(b"src")
    taint_sink(position)  # 将查找结果传递给 taint_sink


def taint_sink(o):
    os.system(str(o))
    


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    bytes_013_T(taint_src)
