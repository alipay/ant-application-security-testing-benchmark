# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
# scene introduction = 循环结构->for_body
# level = 4
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/for_body_solver_001_T
# evaluation information end

import os

def for_body_solver_001_T(taint_src):
    res = None 
    for i in range(2):
        res = taint_src
    taint_sink(res)


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    for_body_solver_001_T(taint_src)

