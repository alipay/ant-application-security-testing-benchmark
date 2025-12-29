# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->多态
# scene introduction = 动态方法绑定
# level = 2
# bind_url = accuracy/context_sensitive/polymorphism/polymorphism_004_F
# evaluation information end
import os

def polymorphism_004_F(taint_src):
    class Base(object):
        pass
    
    class Sub(Base):
        pass
    
    # 动态绑定安全方法
    def dynamic_method(self):
        return u'_'
        
    Sub.dynamic_call = dynamic_method
    
    sub = Sub()
    taint_sink(sub.dynamic_call())  # 输出安全值


def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    polymorphism_004_F(taint_src)

