# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 字典键路径->嵌套
# level = 4
# bind_url = accuracy/field_sensitive/multidimensional_collection/map_mc_001_T
# evaluation information end
import os



def map_mc_001_T(taint_src):
    d = {u"a": {u"b": taint_src}, u"c": {u"d": u"e"}}
    taint_sink(d[u"a"][u"b"])  # 访问污染路径


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_mc_001_T(taint_src)

