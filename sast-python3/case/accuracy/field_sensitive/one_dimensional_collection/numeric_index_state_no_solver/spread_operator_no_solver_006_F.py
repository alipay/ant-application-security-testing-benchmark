# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 列表->列表索引->扩展运算符
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/spread_operator_no_solver_006_F
# evaluation information end
import os

def spread_operator_no_solver_006_F(taint_src):
    array1 = ["a", "b", taint_src]
    array = ["c", *array1]
    taint_sink(array[0])


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    spread_operator_no_solver_006_F(taint_src)

