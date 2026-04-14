# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->动态特性跟踪完整度->反射调用
# scene introduction = 动态创建类
# level = 3
# bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_007_T
# date = 2026-01-08 02:38:35
# evaluation information end
import os


def dynamic_call_reflect_007_T(taint_src):
    Base = type('Base', (object,), {
        '__init__': init
    })

    base = Base(taint_src)

    taint_sink(base.data)


def init(self, data):
    self.data = data


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    dynamic_call_reflect_007_T(taint_src)
