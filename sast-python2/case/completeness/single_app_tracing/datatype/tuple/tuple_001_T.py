# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->元组
# scene introduction = 元组字面量
# level = 2
# bind_url = completeness/single_app_tracing/datatype/tuple/tuple_001_T
# evaluation information end
import os


def tuple_001_T(taint_src):
    t = (taint_src, 2, u"c")  # 使用 Tuple 节点创建元组字面量
    taint_sink(t)


def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    tuple_001_T(taint_src)

