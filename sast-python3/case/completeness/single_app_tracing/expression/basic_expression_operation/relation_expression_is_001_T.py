# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 关系操作->is
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_expression_is_001_T
# evaluation information end

import os


def relation_expression_is_001_T(taint_src):
    value = "taint_src_value"
    result = taint_src is value
    taint_sink(result)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"  # 示例输入，与value相同以演示is操作
    relation_expression_is_001_T(taint_src)
