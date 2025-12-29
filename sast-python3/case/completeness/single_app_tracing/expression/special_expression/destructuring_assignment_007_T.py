# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 解构赋值表达式->对象解构赋值->二维
# level = 2
# bind_url = completeness/single_app_tracing/expression/special_expression/destructuring_assignment_007_T
# evaluation information end
import os
from operator import itemgetter


def destructuring_assignment_007_T(taint_src):
    arr = {'a': '_', 'b': '_', 'c': {taint_src: 'some_value', 'd': 'a'}}
    a, b, c = itemgetter('a', 'b', 'c')(arr)  # 使用 itemgetter 进行解构赋值
    taint_sink(c)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    destructuring_assignment_007_T(taint_src)

