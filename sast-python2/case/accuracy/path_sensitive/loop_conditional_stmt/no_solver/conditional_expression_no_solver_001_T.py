# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
# scene introduction = 条件表达式
# level = 3
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_expression_no_solver_001_T
# evaluation information end
import os


def conditional_expression_no_solver_001_T(taint_src):
    result = taint_src if 2 > 1 else "safe_value"

    taint_sink(result)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    conditional_expression_no_solver_001_T(taint_src)
