# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 列表索引->动态修改
# level = 4
# bind_url = accuracy/field_sensitive/multidimensional_collection/list_mc_005_T
# evaluation information end
import os


def list_mc_005_T(taint_src):
    arr = [["_"]]
    arr.append([taint_src])  # 动态添加污染元素
    taint_sink(arr[1][0])     # 访问动态添加的污染元素


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_mc_005_T(taint_src)

