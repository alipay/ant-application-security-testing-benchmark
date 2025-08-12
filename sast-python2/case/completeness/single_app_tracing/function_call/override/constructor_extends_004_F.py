# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
# scene introduction = 构造函数
# level = 2
# bind_url = completeness/single_app_tracing/function_call/override/constructor_extends_004_F
# evaluation information end
import os


def constructor_extends_004_F(taint_src):
    class BaseClass(object):
     def __init__(self ):
        self.data = u"_"

     def get_data(self):
        return self.data

     def process(self):
        pass

    class DerivedClass(BaseClass): 
     def __init__(self ):
        super(DerivedClass, self).__init__()

     def process(self):
        taint_sink(self.get_data())


    derived = DerivedClass()
    derived.process()



def taint_sink(data):
    os.system(data)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    constructor_extends_004_F(taint_src)

