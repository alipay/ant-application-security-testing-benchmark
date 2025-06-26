# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 字典->字典对象->value为source
# level = 2
# bind_url = accuracy/object_sensitive/collection/map_object_sensitive_002_F
# evaluation information end
import os

def map_object_sensitive_002_F(taint_src):
    map = {}
    map[u"key1"] = taint_src

    m = {}
    m[u"key1"] = u"_"

    taint_sink(m)


def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_object_sensitive_002_F(taint_src)

