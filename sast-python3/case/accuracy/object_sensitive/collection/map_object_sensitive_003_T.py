# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 字典->字典对象->key为source
# level = 2
# bind_url = accuracy/object_sensitive/collection/map_object_sensitive_003_T
# evaluation information end
import os

def map_object_sensitive_003_T(taint_src):
    map = {}
    map[taint_src] = "value"

    map2 = {}
    map2["key"] = "value"

    taint_sink(map)

def taint_sink(o):
    os.system(str(o))

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_object_sensitive_003_T(taint_src)

