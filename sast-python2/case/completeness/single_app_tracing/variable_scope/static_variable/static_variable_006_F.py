# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
# scene introduction = 继承覆盖
# level = 2
# bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_006_F
# evaluation information end
import os


def static_variable_006_F(taint_src):
    class Parent(object):
        static_data = taint_src

    class Child(Parent):
        static_data = u"_"  # 子类覆盖父类静态变量

    taint_sink(Child.static_data)  

def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    static_variable_006_F(taint_src)

