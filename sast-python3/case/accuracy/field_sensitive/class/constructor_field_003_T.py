# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 有参构造函数
# level = 3
# bind_url = accuracy/field_sensitive/class/constructor_field_003_T
# evaluation information end
import os


def constructor_field_003_T(taint_src):
    class A:
        def __init__(self, param1, param2):
            self.data = param1
            self.sani = param2

    obj1 = A(taint_src, "safe_value")
    obj2 = A(taint_src, "safe_value")

    taint_sink(obj1.data)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    constructor_field_003_T(taint_src)
