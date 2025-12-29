# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 解构赋值表达式->数组解构赋值->二维
# level = 2
# bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_006_F
# evaluation information end
import os


def destructuring_assignment_006_F(taint_src):
    arr = [u'_', u'_', [taint_src, u'_']]
    a, b, (c, d) = arr
    taint_sink(d)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    destructuring_assignment_006_F(taint_src)

