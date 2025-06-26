# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 字典->扩展运算符
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_015_F
# evaluation information end
import os

def map_field_sensitive_015_F(taint_src):
    params = {
        'name': 'Tony',
        'age': '12',
        'id': taint_src,
    }
    new_params = {
        'score': 100,
        **params,
    }
    taint_sink(new_params['name'])


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_field_sensitive_015_F(taint_src)

