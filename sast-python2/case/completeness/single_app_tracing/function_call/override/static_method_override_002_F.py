# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
# scene introduction = 静态方法
# level = 2
# bind_url = completeness/single_app_tracing/function_call/override/static_method_override_002_F
# evaluation information end
import os


def static_method_override_002_F(taint_src):

    class Parent(object):
      @staticmethod
      def get_value():
        return u"parent"


    class Child(Parent):
      @staticmethod
      def get_value():  # 隐藏父类静态方法，而非重写
        return taint_src

    Child.get_value()  # 调用子类隐藏的方法
    taint_sink(Parent.get_value())  # 输出 "parent"（未被重写）


def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    static_method_override_002_F(taint_src)

