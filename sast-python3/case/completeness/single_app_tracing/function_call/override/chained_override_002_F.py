# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
# scene introduction = 多层继承
# level = 2
# bind_url = completeness/single_app_tracing/function_call/override/chained_override_002_F
# evaluation information end
import os

def chained_override_002_F(taint_src):
    class A:
     def get_data(self):
        return "A"

    class B(A):
     def get_data(self):
        return super().get_data() + "_B"


    class C(B):
     def get_data(self):
        return super().get_data() + "_C" + "_D"  # 污点扩展


    obj = C()
    taint_sink(obj.get_data())  


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    chained_override_002_F(taint_src)

