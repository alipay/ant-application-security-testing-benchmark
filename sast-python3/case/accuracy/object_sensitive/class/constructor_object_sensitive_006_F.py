# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象
# scene introduction = 接口/类->类定义位置
# level = 2
# bind_url = accuracy/object_sensitive/class/constructor_object_sensitive_006_F
# evaluation information end
import os

class A:
        def __init__(self):
            self.data = '_'

def constructor_object_sensitive_006_F(taint_src):

    obj = A()
    taint_sink(obj.data)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    constructor_object_sensitive_006_F(taint_src)

