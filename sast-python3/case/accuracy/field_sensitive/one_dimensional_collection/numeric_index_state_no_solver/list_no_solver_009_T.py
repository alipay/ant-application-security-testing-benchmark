# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 列表->列表索引
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/list_no_solver_009_T
# evaluation information end
import os


def list_no_solver_009_T(taint_src):
    s = [taint_src, "b", "c"]
    s[1] = "_"
    taint_sink(s)


def taint_sink(o):
    os.system(str(o))

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_no_solver_009_T(taint_src)

