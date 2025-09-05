# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
# scene introduction = 字典/映射（Map）对象
# level = 2
# bind_url = completeness/single_app_tracing/datatype/map/map_007_T
# evaluation information end
import os


def map_007_T(taint_src):
     # 初始化字典
    m = {u"key": u"_",u"src":taint_src}
    # 删除非污染值
    del m[u'key'] 
    
    taint_sink(m)  # 传递更新后的字典


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_007_T(taint_src)

