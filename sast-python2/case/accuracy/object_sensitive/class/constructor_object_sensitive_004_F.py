# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象
# scene introduction = 接口/类->继承对象
# level = 2
# bind_url = accuracy/object_sensitive/class/constructor_object_sensitive_004_F
# evaluation information end
import os


def constructor_object_sensitive_004_F(taint_src):
    class Parent(object):
        def __init__(self, data):
            self.data = data

    class Child(Parent):
        pass

    parent_tainted = Parent(taint_src)
    child_clean = Child(u'_')

    taint_sink(child_clean)  # 传递未污染的子类对象


def taint_sink(o):
    os.system(o.data)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    constructor_object_sensitive_004_F(taint_src)

