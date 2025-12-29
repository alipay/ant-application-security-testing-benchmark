# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_001_T
# evaluation information end
import os


def static_variable_001_T(taint_src):

    def set_static_data(value):
         # set_static_data函数返回一个装饰器函数decorator，该装饰器接收一个类cls作为参数，
         # 并在该类中添加一个名为data的属性，其值设为传入的value。
        def decorator(cls):
            cls.data = value
            return cls
        return decorator

    class A(object):
        pass
    
    A = set_static_data(taint_src)(A)

    taint_sink(A.data)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    static_variable_001_T(taint_src)

