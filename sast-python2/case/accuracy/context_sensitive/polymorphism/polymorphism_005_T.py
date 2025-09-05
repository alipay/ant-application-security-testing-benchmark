# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->多态
# scene introduction = 元类控制继承
# level = 2+
# bind_url = accuracy/context_sensitive/polymorphism/polymorphism_005_T
# evaluation information end
import os

def polymorphism_005_T(taint_src):
    class Sub(object):
        __metaclass__ = Meta
        _taint_src = taint_src  # 使用单下划线
        
    sub = Sub()
    taint_sink(sub.call())  # 输出: taint_src_value


class Meta(type):
    def __new__(cls, name, bases, attrs):
        # 动态注入 call 方法
        def dynamic_call(self):
            return self.__class__._taint_src  # 直接访问单下划线属性
        attrs[u'call'] = dynamic_call
        return super(Meta, cls).__new__(cls, name, bases, attrs)


def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    polymorphism_005_T(taint_src)

