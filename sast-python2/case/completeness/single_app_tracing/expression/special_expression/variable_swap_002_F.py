# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 变量交换
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/variable_swap_002_F
# evaluation information end
import os


def variable_swap_002_F(taint_src):
    a = taint_src
    b = u"_"
    a,b = b,a

    taint_sink(a)

def taint_sink(o):
    os.system(o)  


if __name__ == u'__main__':
    taint_src = u"taint_src_value"  
    variable_swap_002_F(taint_src)
