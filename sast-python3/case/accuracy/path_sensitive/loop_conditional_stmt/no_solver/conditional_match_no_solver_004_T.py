# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
# scene introduction = 区分match准入条件（不需求解）>match->区分具体执行路径->不求解
# level = 3
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_match_no_solver_004_T
# evaluation information end
import os


def conditional_match_no_solver_004_T(taint_src):
    result = 'safe_value'
    match 2:            # 表达式固定为 2
        case 2:         # 匹配，执行此分支
            result = taint_src  # 污染源被赋值给 result
        case _:         # 处理未匹配的分支（避免抛出异常）
            pass

    taint_sink(result)  # 调用 sink result 已被污染


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_match_no_solver_004_T(taint_src)
