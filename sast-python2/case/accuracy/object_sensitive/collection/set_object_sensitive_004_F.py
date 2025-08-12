# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 数组/集合->集合对象->add/discard
# level = 2
# bind_url = accuracy/object_sensitive/collection/set_object_sensitive_004_F
# evaluation information end
import os

def set_object_sensitive_004_F(taint_src):
    polluted_set = set(u'a')  
    # Python 的 add 方法直接添加元素
    polluted_set.add(taint_src)  
    polluted_set.discard(taint_src)  

    taint_sink(polluted_set)  

def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    set_object_sensitive_004_F(taint_src)

