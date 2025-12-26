# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 字典->域敏感->delete函数
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_004_T
# evaluation information end
import os


def map_field_sensitive_004_T(taint_src):
    my_map = {"key1": "safe_value"}
    del my_map['key1']
    my_map["key1"] = taint_src
    taint_sink(my_map.get('key1'))


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_field_sensitive_004_T(taint_src)
