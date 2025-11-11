# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 列表索引->切片后访问
# level = 4
# bind_url = accuracy/field_sensitive/multidimensional_collection/list_slice_002_F
# evaluation information end
import os


def list_slice_002_F(taint_src):
    # 二维列表结构
    arr = [[taint_src, "safe"], ["safe", "safe"]]
    # 使用切片后访问安全元素：不同切片位置的安全数据
    result = arr[1:2][0][0]  # 切片[1:2]得到[["safe", "safe"]]，然后[0][0]访问safe
    taint_sink(result)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_slice_002_F(taint_src)
