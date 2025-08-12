# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 区分类字段
# level = 3
# bind_url = accuracy/field_sensitive/class/constructor_field_006_F
# evaluation information end
import os

def constructor_field_006_F(taint_src):
    a = ClassA(taint_src)
    b = ClassB()
    process(b)  


class ClassA(object):
    def __init__(self, taint_src):
        self.field = taint_src

class ClassB(object):
    def __init__(self):
        self.field = u"SAFE"


def process(obj):
    taint_sink(obj.field)  # 通过对象类型区分字段

def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    constructor_field_006_F(taint_src)

