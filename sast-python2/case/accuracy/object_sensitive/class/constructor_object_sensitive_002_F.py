# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象
# scene introduction = 接口/类->类对象
# level = 2
# bind_url = accuracy/object_sensitive/class/constructor_object_sensitive_002_F
# evaluation information end
import os

def constructor_object_sensitive_002_F(taint_src):
    class A(object):
        def __init__(self, data):
            self.data = data  # 污染源或干净值赋值给实例的 data 属性

    obj = A(taint_src)  # 污染源实例
    o = A(u'_')            # 非污染实例
    taint_sink(o)       # 传递未被污染的实例 o



def taint_sink(o):
     os.system(o.data)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    constructor_object_sensitive_002_F(taint_src)

