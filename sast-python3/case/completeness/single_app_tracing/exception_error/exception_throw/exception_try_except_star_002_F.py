# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
# scene introduction = exception_try_except_star
# level = 2
# bind_url = completeness/chain_tracing/exception_error/exception_throw_except_star/exception_try_except_star_002_F
# evaluation information end


import os
# Python 3.11引入的语法，如果运行环境低于Python 3.11将会报错。不需要的话可以删掉
# from exceptiongroup import ExceptionGroup


def exception_try_except_star_002_F(taint_src):
    try:
        raise ExceptionGroup("Multiple Errors", [
            ValueError(f"Value Error from _"),
            TypeError(f"Type Error from _")
        ])
    except* ValueError as e:
        taint_sink(f"Caught ValueError: {e!s}")
    except* TypeError as e:
        taint_sink(f"Caught TypeError: {e!s}")


def taint_sink(o):
    os.system(str(o))  # 使用os.system执行命令


if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_try_except_star_002_F(taint_src)
