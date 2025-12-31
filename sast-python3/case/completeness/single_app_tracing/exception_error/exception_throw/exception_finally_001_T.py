# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
# scene introduction = exception_finally
# level = 2
# bind_url = completeness/single_app_tracing/exception_error/exception_throw/exception_finally_001_T
# evaluation information end
import os


def exception_finally_001_T(taint_src):
    res = ''
    try:
        raise Exception(taint_src)
    except Exception as e:
        res = e
    finally:
        taint_sink(res)


def taint_sink(o):
    os.system(str(o))


if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_finally_001_T(taint_src)

