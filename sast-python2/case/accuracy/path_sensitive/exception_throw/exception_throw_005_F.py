# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = finally块执行路径
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_throw_005_F
# evaluation information end
import os

# 改
def exception_throw_005_F(taint_src):
    try:
        raise Exception(taint_src)  # 抛出干净异常
    except:
        pass
    finally:
        taint_sink(u"_")  # finally中未污染


def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    exception_throw_005_F(taint_src)

