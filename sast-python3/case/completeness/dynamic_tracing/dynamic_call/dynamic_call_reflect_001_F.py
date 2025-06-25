# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->动态特性跟踪完整度->反射调用
# scene introduction = 字符串常量->反射
# level = 3
# bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_001_F
# evaluation information end
import os


def dynamic_call_reflect_001_F(taint_src):
    class Obj:
        def __init__(self):
            self.name = '_'

    obj = Obj()

    def taint_sink(o):
        os.system(o)

    taint_sink(getattr(obj, 'name'))


# 示例调用
if __name__ == '__main__':
    taint_src = "taint_src_value"
    dynamic_call_reflect_001_F(taint_src)

