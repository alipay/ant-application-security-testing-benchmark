# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 列表索引->切片后访问
# level = 4
# bind_url = accuracy/field_sensitive/multidimensional_collection/list_slice_001_T
# evaluation information end
import os


def list_slice_001_T(taint_src):
    # 二维列表结构
    arr = [[taint_src, "safe"], ["safe", "safe"]]
    # 使用切片后访问：先切片再索引访问
    result = arr[0:1][0][0]  # 切片[0:1]得到[[taint_src, "safe"]]，然后[0][0]访问taint_src
    taint_sink(result)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_slice_001_T(taint_src)
