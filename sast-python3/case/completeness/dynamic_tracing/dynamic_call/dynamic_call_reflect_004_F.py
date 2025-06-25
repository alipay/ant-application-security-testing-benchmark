# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->动态特性跟踪完整度->反射调用
# scene introduction = 字符串常量->反射
# level = 3
# bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_004_F
# evaluation information end
import os


def dynamic_call_reflect_004_F(taint_src):
    class Obj:
        def __init__(self, data):
            self.data = data

    obj = Obj(taint_src)

    setattr(obj, 'foo', '_')

    taint_sink(getattr(obj, 'foo'))


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == '__main__':
    taint_src = "taint_src_value"
    dynamic_call_reflect_004_F(taint_src)

