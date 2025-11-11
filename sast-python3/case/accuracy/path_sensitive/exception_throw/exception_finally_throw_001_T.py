# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = 异常抛出-finally块执行
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_finally_throw_001_T
# evaluation information end
import os

def exception_finally_throw_001_T(taint_src):
    try:
        # try块正常执行，无异常抛出
        pass
    except Exception as e:
        # 不会执行到except块
        pass
    finally:
        # finally块总会执行，处理污点数据
        taint_sink(taint_src)  # 应该检出 - finally块中的污点传递

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_finally_throw_001_T(taint_src)