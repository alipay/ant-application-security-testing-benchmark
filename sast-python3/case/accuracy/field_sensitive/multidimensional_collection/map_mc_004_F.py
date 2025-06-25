# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 递归结构->污染路径
# level = 4+
# bind_url = accuracy/field_sensitive/multidimensional_collection/map_mc_004_F
# evaluation information end
import os



def map_mc_004_F(taint_src):
    d1 = {"a": taint_src,"f":"_"}
    d2 = {"b": d1}
    d1["c"] = d2
    taint_sink(d2["b"]["c"]["b"]["f"])  


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_mc_004_F(taint_src)

