# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 数组->动态修改索引
# level = 3+
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/array_no_solver_004_F
# evaluation information end
import os
import array

def array_no_solver_004_F(taint_src):
    arr = array.array(u'u', [u'a',u'b'])

    char_array = array.array(u'u', taint_src)  # 每个字符作为独立元素
    arr[0] = char_array[0] # 动态修改索引0

    taint_sink(arr[1])  # 访问修改后的污染元素


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    array_no_solver_004_F(taint_src) 

