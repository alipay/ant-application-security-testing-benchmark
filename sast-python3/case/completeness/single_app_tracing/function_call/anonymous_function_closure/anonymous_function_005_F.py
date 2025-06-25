# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
# scene introduction = 直接调用
# level = 2
# bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_005_F
# evaluation information end

import os


def anonymous_function_005_F(taint_src):
    (lambda: taint_sink('safe'))()


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    anonymous_function_005_F(taint_src)

