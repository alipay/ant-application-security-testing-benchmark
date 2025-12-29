# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->元组
# scene introduction = 解构赋值
# level = 2
# bind_url = completeness/single_app_tracing/datatype/tuple/tuple_004_F
# evaluation information end
import os


def tuple_004_F(taint_src):
    items = (taint_src, u"b", u"c")  
    first,second,third = items
    taint_sink(third)


def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    tuple_004_F(taint_src)
