# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
# scene introduction = 静态方法
# level = 2
# bind_url = completeness/single_app_tracing/function_call/override/static_method_override_001_T
# evaluation information end
import os


def static_method_override_001_T(taint_src):

    class Parent:
      @staticmethod
      def get_value():
        return "parent"


    class Child(Parent):
      @staticmethod
      def get_value():  
        return taint_src

    taint_sink(Child.get_value())  


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    static_method_override_001_T(taint_src)

