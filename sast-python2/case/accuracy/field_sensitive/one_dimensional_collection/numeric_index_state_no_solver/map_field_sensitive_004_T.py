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
    my_map = {}  
    my_map[u'key1'] = taint_src  # 污染源绑定到 'key1'
    my_map[u'key2'] = u'value'      # 初始设置 'key2' 为干净值
    my_map[u'key2'] = taint_src  # 覆盖 'key2' 为污染源
    del my_map[u'key1']            # 删除 'key1' 键值对
    taint_sink(my_map.get(u'key2'))  # 通过 'key2' 获取污染源


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_field_sensitive_004_T(taint_src)

