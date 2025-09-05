# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
# scene introduction = add方法
# level = 2
# bind_url = completeness/single_app_tracing/datatype/collections/set_004_F
# evaluation information end
import os

def set_004_F(taint_src):
    s = set()
    s.add(u"_")  # 添加污点元素
    taint_sink(s)

def taint_sink(o):
    os.system(unicode(o))

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    set_004_F(taint_src)

