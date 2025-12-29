# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 关键字参数
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_keyword_taint_001_T
# evaluation information end
import os


def argument_passing_keyword_taint_001_T(taint_src):
    def process(**kwargs):
        taint_sink(kwargs['data'])  # 关键字参数捕获污点

    process(data=taint_src)  # 显式传递污点

def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    argument_passing_keyword_taint_001_T(taint_src)  

