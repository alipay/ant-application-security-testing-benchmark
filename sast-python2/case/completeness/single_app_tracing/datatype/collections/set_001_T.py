# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
# scene introduction = 集合（Set）对象
# level = 2
# bind_url = completeness/single_app_tracing/datatype/collections/set_001_T
# evaluation information end
import os


def set_001_T(taint_src):
    # 将污染源转换为集合对象
    s = set(taint_src)
    taint_sink(s)


def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    # 使用固定的污染源
    set_001_T(taint_src)

