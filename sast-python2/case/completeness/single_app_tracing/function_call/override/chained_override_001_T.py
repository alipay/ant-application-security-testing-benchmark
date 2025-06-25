# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
# scene introduction = 多层继承
# level = 2
# bind_url = completeness/single_app_tracing/function_call/override/chained_override_001_T
# evaluation information end
import os

def chained_override_001_T(taint_src):
    class A(object):
     def get_data(self):
        return u"A"

    class B(A):
     def get_data(self):
        return super(B, self).get_data() + u"_B"


    class C(B):
     def get_data(self):
        return super(C, self).get_data() + u"_C" + taint_src  # 污点扩展


    obj = C()
    taint_sink(obj.get_data())  # 输出 "A_B_C_#taint_src_value"


def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    chained_override_001_T(taint_src)

