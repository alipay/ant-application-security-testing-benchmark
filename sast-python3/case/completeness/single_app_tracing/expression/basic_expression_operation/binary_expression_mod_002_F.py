# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->取余
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_mod_002_F
# evaluation information end
import os


def binary_expression_mod_002_F(taint_src):
    result = taint_src % 3  # 取余运算（假设__taint_src为整数）
    taint_sink("_")  

def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = 7
    binary_expression_mod_002_F(taint_src)

