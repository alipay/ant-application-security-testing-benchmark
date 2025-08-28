# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
# scene introduction = 区分match准入条件（不需求解）->match->区分分支
# level = 3
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_match_no_solver_002_F
# evaluation information end
import os


def conditional_match_no_solver_002_F(taint_src):
    data = ''
    match 2:            # 表达式固定为 2
        case 1:         # 不匹配，不会执行
            taint_sink(data)
        case _:         # 默认分支
            data = taint_src  # 污染源被赋值给 data，但未传递到 sink



def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_match_no_solver_002_F(taint_src)

