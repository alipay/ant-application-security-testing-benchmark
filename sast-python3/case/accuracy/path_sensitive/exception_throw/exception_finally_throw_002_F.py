# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = 异常抛出-finally块执行
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_finally_throw_002_F
# evaluation information end
import os

def exception_finally_throw_002_F(taint_src):
    try:
        # try块正常执行，无异常抛出
        pass
    except Exception as e:
        # 不会执行到except块
        pass
    finally:
        # finally块总会执行，但传递安全数据
        safe_data = "_"
        taint_sink(safe_data)  # 不应检出 - finally块中传递安全数据

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_finally_throw_002_F(taint_src)