# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
# scene introduction =
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_001_T
# evaluation information end
import os


def static_variable_001_T(taint_src):
    class A:
        data = taint_src

    taint_sink(A.data)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    static_variable_001_T(taint_src)
