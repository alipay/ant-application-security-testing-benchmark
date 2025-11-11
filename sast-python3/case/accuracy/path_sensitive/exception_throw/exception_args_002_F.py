# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = 异常抛出-多参数异常对象
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_args_002_F
# evaluation information end
import os

def exception_args_002_F(taint_src):
    try:
        # 创建多参数异常，但污点数据不在被访问的位置
        raise Exception("Error message", "_", taint_src)
    except Exception as e:
        # 获取异常的所有参数
        args = e.args
        # args[1] 是安全数据，args[2] 是污点但不被访问
        taint_sink(args[1])  # 不应检出 - 访问的是安全参数

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_args_002_F(taint_src)