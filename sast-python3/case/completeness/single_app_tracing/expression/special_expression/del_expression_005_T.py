# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = del运算符->字典键值对
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/del_expression_005_T
# evaluation information end
import os



def del_expression_005_T(taint_src):
    dct = {"key1": taint_src,"key2":"aaa"}  # 初始化字典，包含 __taint_src 的值
    del dct["key2"]  # 删除字典中的键值对
    taint_sink(dct)  # 将修改后的字典传递给 sink 点


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    del_expression_005_T(taint_src)
