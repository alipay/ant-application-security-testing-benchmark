# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 关系操作->not in
# level = 2+
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_expression_not_in_001_T
# evaluation information end

import os


def relation_expression_not_in_001_T(taint_src):
    container = ["apple", "banana", "cherry"]
    result = taint_src not in container
    taint_sink(result)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"  # 示例输入，不在container中
    relation_expression_not_in_001_T(taint_src)
