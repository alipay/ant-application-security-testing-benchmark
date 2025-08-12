# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 解构赋值表达式->对象解构赋值->二维
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_008_F
# evaluation information end
import os


def destructuring_assignment_008_F(taint_src):
    arr = {u'a': u'_', u'b': u'_', u'c': {u'value': taint_src, u'd': u'a'}}
    a, b, c = arr[u'a'], arr[u'b'], arr[u'c'][u'value']  # 解构赋值，从字典中提取值
    taint_sink(a)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    destructuring_assignment_008_F(taint_src)

