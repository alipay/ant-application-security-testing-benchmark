# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
# scene introduction = 条件表达式
# level = 4
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/conditional_expression_solver_002_F
# evaluation information end
import os


def conditional_expression_solver_002_F(taint_src):
    result = taint_src if 1 + 1 < 1 else "safe_value"

    taint_sink(result)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    conditional_expression_solver_002_F(taint_src)
