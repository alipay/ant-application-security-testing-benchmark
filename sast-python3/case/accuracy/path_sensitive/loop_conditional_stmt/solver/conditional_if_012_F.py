# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
# scene introduction = 区分if else分支+准入条件（需要求解）->if->区分具体执行路径->求解
# level = 4
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/conditional_if_012_F
# evaluation information end
import os


def conditional_if_012_F(taint_src):
    res = ""
    if 1 + 1 == 3:
        res = taint_src
    else:
        res = "_"

    taint_sink(res)



def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_if_012_F(taint_src)

