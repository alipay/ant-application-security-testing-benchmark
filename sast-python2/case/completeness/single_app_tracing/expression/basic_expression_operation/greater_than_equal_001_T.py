# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 关系操作->大于等于
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/greater_than_equal_001_T
# evaluation information end

import os


def greater_than_equal_001_T(taint_src):
    result = taint_src >= 5
    taint_sink(result)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = 7  # 示例输入，应确保可以被安全地转换为int
    greater_than_equal_001_T(taint_src)
