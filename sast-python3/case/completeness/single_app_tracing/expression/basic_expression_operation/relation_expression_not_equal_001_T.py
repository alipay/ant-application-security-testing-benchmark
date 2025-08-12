# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 关系操作->不等于
# level = 2+
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_expression_not_equal_001_T
# evaluation information end

import os


def relation_expression_not_equal_001_T(taint_src):
    # 检查__taint_src是否不等于特定值
    result = taint_src != '__another_taint_src_value'
    taint_sink(result)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"  # 示例输入，应与比较值不同以演示不等于操作
    relation_expression_not_equal_001_T(taint_src)
