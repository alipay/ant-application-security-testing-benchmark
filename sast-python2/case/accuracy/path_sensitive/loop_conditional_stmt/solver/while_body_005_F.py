# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
# scene introduction = 循环结构->whileElse
# level = 4
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/while_body_005_F
# evaluation information end

import os


def while_body_005_F(taint_src):
    i = 7
    res = u"_"
    while i < 3:
        res = taint_src
        break
    else:
        # 当while循环条件不满足时执行
        taint_sink(res)
    

def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    while_body_005_F(taint_src)
