# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 列表索引->二维
# level = 4
# bind_url = accuracy/field_sensitive/multidimensional_collection/list_mc_002_F
# evaluation information end
import os


def list_mc_002_F(taint_src):
    s = [[taint_src], ["b"], "c"]
    taint_sink(s[1])


def taint_sink(o):
    os.system(str(o))

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_mc_002_F(taint_src)

