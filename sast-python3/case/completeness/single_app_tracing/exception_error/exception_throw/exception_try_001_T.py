# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
# scene introduction = exception_try
# level = 2
# bind_url = completeness/single_app_tracing/exception_error/exception_throw/exception_try_001_T
# evaluation information end
import os


def exception_try_001_T(taint_src):
    try:
        taint_sink(taint_src)
        raise Exception('_')  # 使用Exception类来抛出异常
    except Exception as e:
        pass  # 空的except块，与JavaScript中的catch块相同


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_try_001_T(taint_src)

