# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 继承覆盖父类字段
# level = 3
# bind_url = accuracy/field_sensitive/class/inheritance_002_F
# evaluation information end
import os

def inheritance_002_F(taint_src):
    parent_obj = Parent(taint_src)
    child_obj = Child()
    taint_sink(child_obj.field)  # 不应检测到污点


class Parent:
    def __init__(self, taint_src):
        self.field = taint_src  # 父类字段携带污点

class Child(Parent):
    def __init__(self):
        super().__init__("SAFE")  # 子类覆盖父类字段为安全值
        self.field = "SAFE"

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    inheritance_002_F(taint_src)

