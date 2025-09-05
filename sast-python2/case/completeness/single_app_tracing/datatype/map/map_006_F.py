# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
# scene introduction = 字典/映射（Map）对象
# level = 2
# bind_url = completeness/single_app_tracing/datatype/map/map_006_F
# evaluation information end
import os


def map_006_F(taint_src):
     # 初始化干净字典
    m = {u"key": taint_src}
    # 更新为污染值
    m.update({u"key":u"_" })
    
    taint_sink(m)  # 传递更新后的污染字典


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_006_F(taint_src)

