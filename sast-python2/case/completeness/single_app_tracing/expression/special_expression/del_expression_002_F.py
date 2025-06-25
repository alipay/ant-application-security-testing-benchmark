# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = del运算符->对象field
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/del_expression_002_F
# evaluation information end
import os


def del_expression_002_F(taint_src):
    employee = {
        u'firstname': u'Bob',
        u'lastname': taint_src,
    }

    del employee[u'lastname']  # 删除 lastname 属性
    taint_sink(employee.get(u'lastname'))  # 由于 lastname 已被删除，这将返回 None


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    del_expression_002_F(taint_src)

