# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = 异常抛出-多参数异常对象
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_args_001_T
# evaluation information end
import os

def exception_args_001_T(taint_src):
    try:
        # 创建多参数异常，第二个参数是污点
        raise Exception("Error message", taint_src, "_")
    except Exception as e:
        # 获取异常的所有参数
        args = e.args
        # args[1] 是污点数据
        taint_sink(args[1])  # 应该检出 - 异常参数中的污点数据

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_args_001_T(taint_src)