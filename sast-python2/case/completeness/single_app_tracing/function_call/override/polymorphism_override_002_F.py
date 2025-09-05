# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
# scene introduction = 多态
# level = 2
# bind_url = completeness/single_app_tracing/function_call/override/polymorphism_override_002_F
# evaluation information end
import os


def polymorphism_override_002_F(taint_src):

    class Base(object):
     def call(self):
        raise NotImplementedError(u"Subclasses must implement this method")


    class Sub1(Base):
     def call(self):
        return taint_src


    class Sub2(Base):
     def call(self):
        return u"_"

    sub = Sub2()
    taint_sink(sub.call())


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    polymorphism_override_002_F(taint_src)

