# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 解构赋值表达式->对象解构赋值
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_003_T
# evaluation information end
import os


def destructuring_assignment_003_T(taint_src):
    arr = {u'a': u'_', u'b': u'_', u'c': taint_src}
    a = arr[u'a']
    b = arr[u'b']
    c = arr[u'c']
    taint_sink(c)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    destructuring_assignment_003_T(taint_src)

