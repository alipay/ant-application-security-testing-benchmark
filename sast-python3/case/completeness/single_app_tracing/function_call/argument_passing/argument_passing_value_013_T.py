# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 类型注解
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_013_T
# evaluation information end

import os

def argument_passing_value_013_T(taint_src: str):
    def process(arg1: str,arg2: str):
        taint_sink(arg1)

    process(taint_src,"_")


def taint_sink(o: str):
    os.system(o)  


if __name__ == "__main__":
    taint_src = "taint_src_value"
    argument_passing_value_013_T(taint_src)
