# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
# scene introduction = 集合（Set）对象
# level = 2
# bind_url = completeness/single_app_tracing/datatype/collections/set_002_F
# evaluation information end
import os


def set_002_F(taint_src):
    # 创建一个与污染源无关的集合
    s = set('_')  # 固定值，不依赖污染源
    taint_sink(s)  # Sink 接收到的是无污染数据


def taint_sink(o):
    os.system(str(o))


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    set_002_F(taint_src)  # 输入值不会影响集合内容

