# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
# scene introduction = 关键字参数
# level = 2
# bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_keyword_taint_002_F
# evaluation information end
import os


def argument_passing_keyword_taint_002_F(taint_src):
    def process(**kwargs):
        taint_sink(kwargs[u'data'])  # 关键字参数捕获污点

    process(data=u"_")  # 显式传递污点

def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    argument_passing_keyword_taint_002_F(taint_src)  

