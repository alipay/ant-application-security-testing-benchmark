# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = del运算符->切片
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/del_expression_012_F
# evaluation information end
import os



def del_expression_012_F(taint_src):
    lst = [taint_src, u"b", u"c", u"d"]  # 初始化列表，包含 __taint_src 的值
    del lst[0:1]  # 删除列表的切片
    taint_sink(lst)  # 将修改后的列表传递给 sink 点


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    del_expression_012_F(taint_src)
