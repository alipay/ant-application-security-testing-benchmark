# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
# scene introduction = for循环
# level = 3
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/loop_for_no_solver_002_F
# evaluation information end
import os


def loop_for_no_solver_002_F(taint_src):
    list = ["safe_value"]
    for i in list:
        taint_sink(i)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    loop_for_no_solver_002_F(taint_src)
