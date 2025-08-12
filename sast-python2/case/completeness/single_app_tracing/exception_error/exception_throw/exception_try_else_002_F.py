# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出和捕获
# scene introduction = exception_try_else
# level = 2+
# bind_url = completeness/chain_tracing/exception_error/exception_throw/exception_try_else_002_F
# evaluation information end


import os


def exception_try_else_002_F(taint_src):
    try:
        pass
    except Exception, e:
        # 异常发生时的处理逻辑
        pass
    else:
        # 没有异常时执行的逻辑
        taint_sink(u"_")


def taint_sink(o):
    os.system(o)  


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    exception_try_else_002_F(taint_src)
