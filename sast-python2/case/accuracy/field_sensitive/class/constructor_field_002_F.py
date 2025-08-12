# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 无参构造函数
# level = 3
# bind_url = accuracy/field_sensitive/class/constructor_field_002_F
# evaluation information end
import os

def constructor_field_002_F(taint_src):
    class A(object):
        def __init__(self):
            self.data = taint_src  # 初始化 data 属性为传入的参数
            self.sani = u'_'          # 初始化 sani 属性为下划线
            
    obj = A()                       # 创建类 A 的实例
    taint_sink(obj.sani)          # 调用 taint_sink，传入实例的 sani 属性



def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    constructor_field_002_F(taint_src)

