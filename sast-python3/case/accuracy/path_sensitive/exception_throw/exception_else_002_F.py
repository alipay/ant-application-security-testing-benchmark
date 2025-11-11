# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = 异常抛出-else块执行
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_else_002_F
# evaluation information end
import os

def exception_else_002_F(taint_src):
    try:
        # 正常执行，不抛出异常
        normal_data = "_"
    except Exception as e:
        # 不会执行到except块
        pass
    else:
        # 无异常时执行else块，但传递安全数据
        safe_data = "_"
        taint_sink(safe_data)  # 不应检出 - else块中传递安全数据

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_else_002_F(taint_src)