# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 字典键路径->get方法链
# level = 4
# bind_url = accuracy/field_sensitive/multidimensional_collection/map_mc_005_T
# evaluation information end
import os


def map_mc_005_T(taint_src):
    d = {"a": {"b": {"c": taint_src}}, "x": {"y": {"z": "safe"}}}
    # 使用get方法链式访问嵌套字典
    result = d.get("a", {}).get("b", {}).get("c")
    taint_sink(result)  # 应该检测到污染


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_mc_005_T(taint_src)
