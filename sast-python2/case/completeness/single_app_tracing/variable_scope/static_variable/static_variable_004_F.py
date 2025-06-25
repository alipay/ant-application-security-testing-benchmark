# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
# scene introduction = 跨类访问-static变量
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_004_F
# evaluation information end
import os


def static_variable_004_F(taint_src):
    A.data = taint_src  # 设置静态变量
    a = A()
    a.processA()

class A(object):
    data = None

    def processA(self):
        self.test()

    def test(self):
        A.data = u'_'
        B().processB()


class B(object):
    @staticmethod
    def processB():
        data = A.data
        taint_sink(data)



def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    static_variable_004_F(taint_src)

