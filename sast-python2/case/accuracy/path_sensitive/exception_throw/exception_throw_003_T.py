# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = 异常抛出-except块
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_throw_003_T
# evaluation information end
import os


def exception_throw_003_T(taint_src):
    try:
        raise Exception(taint_src)
    except Exception, e:
        taint_sink(e)



def taint_sink(o):
    os.system(unicode(o))

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    exception_throw_003_T(taint_src)

