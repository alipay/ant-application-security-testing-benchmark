# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 字典->域敏感
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_003_F
# evaluation information end
import os


def map_field_sensitive_003_F(taint_src):
    my_map = {}  
    my_map[taint_src] = u'value1'  # 污染源作为键，值为干净的 'value1'
    my_map[u'key2'] = u'value2'       # 设置另一个键值对（非污染源）
    taint_sink(my_map.get(taint_src))  # 通过污染的键获取值（值本身是干净的）




def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_field_sensitive_003_F(taint_src)

