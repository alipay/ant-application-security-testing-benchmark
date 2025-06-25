# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 逻辑表达式->与表达式
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/logic_expression_and_001_F
# evaluation information end
import os


def logic_expression_and_001_F(taint_src):
    result = taint_src and "aa"
    taint_sink(result)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    logic_expression_and_001_F(taint_src)

