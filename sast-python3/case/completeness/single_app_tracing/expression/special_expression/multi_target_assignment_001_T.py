# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 多目标赋值
# level = 2
# bind_url = completeness/single_app_tracing/expression/special_expression/multi_target_assignment_001_T
# evaluation information end
import os


def multi_target_assignment_001_T(taint_src):
    # 将taint_src的值同时赋给多个变量
    a = b = c = d = taint_src
    e = f = "_"
    
    # 使用其中一个变量作为sink点
    taint_sink(c)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    multi_target_assignment_001_T(taint_src)
