# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
# scene introduction = exception_catch
# level = 2
# bind_url = completeness/single_app_tracing/exception_error/exception_throw/exception_catch_002_F
# evaluation information end
import os

def exception_catch_002_F(taint_src):
    try:
        # 抛出一个包含 message 和 code 的异常对象
        raise Exception({'message': "_", 'code': 123})
    except Exception as e:
        # 捕获异常并提取 message 属性
        error_info = e.args[0]  # 获取抛出的异常内容（这里是字典）
        taint_sink(error_info['message'])  # 将 message 传递给污染接收函数


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_catch_002_F(taint_src)

