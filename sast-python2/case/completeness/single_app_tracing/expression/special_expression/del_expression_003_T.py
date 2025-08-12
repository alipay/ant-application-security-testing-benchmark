# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = del运算符->数组索引
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/del_expression_003_T
# evaluation information end
import os


def del_expression_003_T(taint_src):
    array = [taint_src, u'b', u'c', u'd']
    del array[1]  # 模拟删除操作，将第二个元素设置为None
    taint_sink(array)


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    del_expression_003_T(taint_src)

