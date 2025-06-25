# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象
# scene introduction = 接口/类->继承对象
# level = 2
# bind_url = accuracy/object_sensitive/class/constructor_object_sensitive_003_T
# evaluation information end
import os


def constructor_object_sensitive_003_T(taint_src):
    class Parent:
        def __init__(self, data):
            self.data = data

    class Child(Parent):
        pass

    # 污染父类实例
    parent_tainted = Parent(taint_src)
    # 非污染子类实例
    child_clean = Child('_')

    taint_sink(parent_tainted)  # 传递污染的父类对象


def taint_sink(o):
    os.system(o.data)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    constructor_object_sensitive_003_T(taint_src)

