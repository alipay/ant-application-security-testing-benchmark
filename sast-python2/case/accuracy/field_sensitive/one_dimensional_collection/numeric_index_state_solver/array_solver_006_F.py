# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值非数字的场景，能够区分不同索引上特定元素的状态（需要求解）
# scene introduction = 数组->数组索引->需求解
# level = 4+
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_solver/array_solver_006_F
# evaluation information end
import os
import array

def array_solver_006_F(taint_src):
    # 将字符串拆分为单个字符存储（无法完整保留原始字符串为一个元素）
    char_array = array.array(u'u', taint_src)  # 每个字符作为独立元素
    # 创建包含其他元素的数组（需统一为字符类型）
    s = array.array(u'u', [u'c', u'b', char_array[0]])  # 仅保留首字符
    taint_sink(s[0 + 1])

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    array_solver_006_F(taint_src)  # 输出：_（仅第一个字符）

