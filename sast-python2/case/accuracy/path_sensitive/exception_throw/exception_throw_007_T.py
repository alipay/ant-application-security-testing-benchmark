# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = else块
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_throw_007_T
# evaluation information end
import os


def exception_throw_007_T(taint_src):
    try:
        pass
    except:
        pass
    else:
        taint_sink(taint_src)


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    exception_throw_007_T(taint_src)
