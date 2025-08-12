# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 参数列表1
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_various_types_003_T
# evaluation information end

import os


def argument_passing_various_types_003_T(taint_src):
    def process(a, b=2, *args, **kwargs):
        taint_sink(b)

    # 调用过程，展示各种参数的使用方式
    process(b=taint_src, a=1, c=u"critical", data=taint_src)


def taint_sink(o):
    os.system(o)  


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    argument_passing_various_types_003_T(taint_src)
