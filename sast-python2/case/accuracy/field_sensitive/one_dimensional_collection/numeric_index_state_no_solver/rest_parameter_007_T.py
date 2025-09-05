# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 列表->列表索引->剩余参数
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/rest_parameter_007_T
# evaluation information end
import os


def rest_parameter_007_T(taint_src):
    collectArgs(u"prefix", taint_src, u"suffix")


def collectArgs(*args):
    taint_sink(args[1])


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    rest_parameter_007_T(taint_src)

