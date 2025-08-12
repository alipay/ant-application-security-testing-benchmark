# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 有参构造函数
# level = 3
# bind_url = accuracy/field_sensitive/class/constructor_field_004_F
# evaluation information end
import os

def constructor_field_004_F(taint_src):
    class A(object):
        def __init__(self, param):
            self.data = param          
            self.sani = u'_'            
            
    obj = A(taint_src)               
    taint_sink(obj.sani)             


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    constructor_field_004_F(taint_src)

