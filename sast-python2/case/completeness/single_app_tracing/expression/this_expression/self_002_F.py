# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->this表达式
# scene introduction = self
# level = 2
# bind_url = completeness/single_app_tracing/expression/this_expression/self_002_F
# evaluation information end
import os


class Base(object):
    def __init__(self, data):
        self.data = "safe_value"


def self_002_F(taint_src):
    obj = Base(taint_src)
    taint_sink(obj.data)


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    self_002_F(taint_src)
