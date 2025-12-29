# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 数组/集合->数组对象
# level = 2
# bind_url = accuracy/object_sensitive/collection/array_object_sensitive_002_F
# evaluation information end


import os
import array

def array_object_sensitive_002_F(taint_src):
    char_array = array.array(u'u', taint_src)  # 每个字符作为独立元素
    s = array.array(u'u', [u'c', u'b', char_array[0]])
    s2 = array.array(u'u', [u'a', u'b', u'c'])
    taint_sink(s2)

def taint_sink(o):
    os.system(u''.join(o))

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    array_object_sensitive_002_F(taint_src)  # 输出：_（仅第一个字符）

