# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->多态
# scene introduction = 元类控制继承
# level = 2
# bind_url = accuracy/context_sensitive/polymorphism/polymorphism_006_F
# evaluation information end
import os

def polymorphism_006_F(taint_src):
    class Sub(object):
        __metaclass__ = Meta
        _taint_src = taint_src  # 使用单下划线
    
    sub = Sub()
    taint_sink(sub.call())  # 输出安全值


class Meta(type):
    def __new__(cls, name, bases, attrs):
        # 动态注入安全方法
        def dynamic_call(self):
            return u'_'
        attrs[u'call'] = dynamic_call
        return super(Meta, cls).__new__(cls, name, bases, attrs)


def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    polymorphism_006_F(taint_src)

