# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->private变量
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/private_variable/private_variable_001_T
# evaluation information end
import os


def private_variable_001_T(taint_src):
    class A(object):
        def __init__(self, data):
            self.__data = data

        def get_data(self):
            return self.__data

        def set_data(self, data):
            self.__data = data

    o = A(data=taint_src)  # 显式传递 taint_src
    taint_sink(o.get_data())


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    private_variable_001_T(taint_src)

