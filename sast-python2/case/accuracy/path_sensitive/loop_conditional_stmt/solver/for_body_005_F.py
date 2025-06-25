# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
# scene introduction = 循环结构->for_body
# level = 4
# bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/for_body_005_F
# evaluation information end
import os


def for_body_005_F(taint_src):
    res = None  # 初始化变量（避免未定义错误） 
    for i in xrange(0):  # 循环条件不满足，循环体不会执行
        res = taint_src  # 这一行永远不会执行
    taint_sink(res)  # 调用 sink 时，res 保持初始值 None（未被污染）



def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    for_body_005_F(taint_src)

