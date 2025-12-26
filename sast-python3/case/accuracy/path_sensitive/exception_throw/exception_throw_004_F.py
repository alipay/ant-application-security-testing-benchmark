# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = 异常抛出-except块
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_throw_004_F
# evaluation information end
import os


def exception_throw_004_F(taint_src):
    try:
        raise Exception("safe_value")
    except Exception as e:
        taint_sink(e)


def taint_sink(o):
    os.system(str(o))


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_throw_004_F(taint_src)
