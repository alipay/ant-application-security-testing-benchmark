# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
# scene introduction = 跨类访问-static变量
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_003_T
# evaluation information end
import os

def static_variable_003_T(taint_src):

    class A(object):
        def __init__(self, data):
            self.data = data  # 实例变量

        def processA(self):
            self.test()

        def test(self):
            B().processB(self.data)

    class B(object):
        @staticmethod
        def processB(data):
           taint_sink(data) 

    a = A(taint_src)  # 通过构造函数传递 taint_src
    a.processA()


def taint_sink(o):
    os.system(o)

if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    static_variable_003_T(taint_src)

