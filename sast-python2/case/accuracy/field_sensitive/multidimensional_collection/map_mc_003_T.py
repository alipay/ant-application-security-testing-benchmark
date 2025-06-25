# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 递归结构->污染路径
# level = 4+
# bind_url = accuracy/field_sensitive/multidimensional_collection/map_mc_003_T
# evaluation information end
import os



def map_mc_003_T(taint_src):
    d1 = {u"a": taint_src,u"f":u"_"}
    d2 = {u"b": d1}
    d1[u"c"] = d2
    taint_sink(d2[u"b"][u"c"][u"b"][u"a"])  # 递归路径访问污染


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_mc_003_T(taint_src)

