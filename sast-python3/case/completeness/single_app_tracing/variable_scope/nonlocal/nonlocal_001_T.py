# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->变量声明
# scene introduction = 修改作用域
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/nonlocal/nonlocal_001_T
# evaluation information end


import os


def nonlocal_001_T(taint_src):
    x = taint_src+"outer"

    def inner_function():
        nonlocal x  # 声明 x 为外层作用域的变量
        x = taint_src+"inner"  # 修改外层作用域中的 x
        taint_sink(x)

    inner_function()    # 调用内层函数


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    nonlocal_001_T(taint_src)

