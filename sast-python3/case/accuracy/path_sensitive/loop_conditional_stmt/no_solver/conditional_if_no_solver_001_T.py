# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
# scene introduction = 区分if else准入条件（不需求解）->if->区分分支
# level = 3
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_if_no_solver_001_T
# evaluation information end
import os


def conditional_if_no_solver_001_T(taint_src):
    if True:
        taint_sink(taint_src)
    else:
        taint_sink("safe_value")


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_if_no_solver_001_T(taint_src)
