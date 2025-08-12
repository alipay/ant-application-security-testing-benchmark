# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
# scene introduction = 直接操作类属性
# level = 2
# bind_url = completeness/single_app_tracing/class/simple_object/simple_object_004_F
# evaluation information end
import os


def simple_object_004_F(taint_src):
    class Person(object):
        pass

    Person.name = u"abc"

    # 创建Person的实例
    person = Person()

    # 传递person的name属性给taint_sink函数
    taint_sink(person.name)

def taint_sink(o):
    os.system(o)

if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    simple_object_004_F(taint_src)


