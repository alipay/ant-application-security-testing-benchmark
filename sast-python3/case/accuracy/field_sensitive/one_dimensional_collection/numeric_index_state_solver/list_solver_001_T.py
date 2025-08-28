# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值非数字的场景，能够区分不同索引上特定元素的状态（需要求解）
# scene introduction = 列表->列表索引->需求解
# level = 4
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_solver/list_solver_001_T
# evaluation information end
import os


def list_solver_001_T(taint_src):
    s = ["a", "b", taint_src, "c", "d"]
    taint_sink(s[1 + 1])

def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_solver_001_T(taint_src)

