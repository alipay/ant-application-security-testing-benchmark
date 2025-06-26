# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
# scene introduction = 交集-并集
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/collections/set_006_F
# evaluation information end
import os

def set_006_F(taint_src):
    set1 = set([taint_src, u'a', u'b'])
    set2 = set([u'a', u"b", u'c'])
    result = set1.intersection(set2)  # 交集包含污点
    taint_sink(result)
    
def taint_sink(o):
    os.system(unicode(o))

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    set_006_F(taint_src)

