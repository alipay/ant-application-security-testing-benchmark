# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
# scene introduction = 区分match准入条件（需要求解）->match->区分具体执行路径->求解
# level = 4
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/conditional_match_solver_002_T
# evaluation information end
import os


def conditional_match_solver_002_T(taint_src):
    data = ''
    match 1 + 1:  # 表达式计算为 2
        case 2:    # 匹配成功，执行分支
            data = taint_src  # 污点传播路径（触发）
    taint_sink(data)  # data 被污染为 taint_src



def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_match_solver_002_T(taint_src)

