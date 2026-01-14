# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出和捕获
# scene introduction = exception_raise_string
# level = 2
# bind_url = completeness/single_app_tracing/exception_error/exception_throw/exception_catch_004_F
# date = 2026-01-07 02:25:05
# evaluation information end
import os


def exception_catch_004_F(taint_src):
    try:
        raise Exception("safe_value")
    except Exception as e:
        taint_sink(e)


def taint_sink(o):
    os.system(str(o))


if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_catch_004_F(taint_src)