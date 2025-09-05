# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 字典->解构赋值
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_010_T
# evaluation information end
import os

def map_field_sensitive_010_T(taint_src):
    arr = {
        u'a': u'_',
        u'b': u'_',
        u'c': {u'taint_src': taint_src, u'd': u'a'}
    }
    a = arr[u'a']
    b = arr[u'b']
    c = arr[u'c']
    taint_sink(c[u'taint_src'])  # 传递嵌套字典中的 taint_src


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    map_field_sensitive_010_T(taint_src)

