# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 捕获剩余值
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/rest_parameter_002_F
# evaluation information end
import os


def rest_parameter_002_F(taint_src):
    foo = taint_src
    bar = "hello world"
    r1, r2, *rest = [123, 456, foo, bar]
    taint_sink(r1)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    rest_parameter_002_F(taint_src)

