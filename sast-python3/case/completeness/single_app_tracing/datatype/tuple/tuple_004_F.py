# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->元组
# scene introduction = 解包操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/tuple/tuple_004_F
# evaluation information end
import os


def tuple_004_F(taint_src):
    items = ("_", "b", "c")
    *unpacked, last = items  # 使用 Tuple 节点进行解包操作
    taint_sink(unpacked)


def taint_sink(o):
    os.system(str(o))


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    tuple_004_F(taint_src)
