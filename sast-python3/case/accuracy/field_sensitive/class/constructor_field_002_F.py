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
    class A:
        def __init__(self):
            self.data = taint_src
            self.sani = 'safe_value'

    obj1 = A()
    obj2 = A()
    taint_sink(obj2.sani)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    constructor_field_002_F(taint_src)
