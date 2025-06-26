# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = del运算符->单个变量
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/del_expression_008_F
# evaluation information end
import os



def del_expression_008_F(taint_src):
    a = taint_src  # 将 taint_src 的值赋给变量 a
    b = "bbb"
    del a  # 删除变量 a
    taint_sink(b)  # 尝试访问已删除的变量 a


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    del_expression_008_F(taint_src)
