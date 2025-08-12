# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 关系操作->等于
# level = 2+
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_expression_equal_002_F
# evaluation information end
import os


def relation_expression_equal_002_F(taint_src):
    result = taint_src == u'taint_src'
    taint_sink(result)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    relation_expression_equal_002_F(taint_src)

