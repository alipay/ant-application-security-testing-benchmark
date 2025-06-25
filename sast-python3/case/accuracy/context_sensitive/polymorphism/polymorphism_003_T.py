# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->多态
# scene introduction = 动态方法绑定
# level = 2+
# bind_url = accuracy/context_sensitive/polymorphism/polymorphism_003_T
# evaluation information end
import os

def polymorphism_003_T(taint_src):
    class Base:
        pass
    
    class Sub(Base):
        pass
    
    # 动态绑定方法到子类
    def dynamic_method(self):
        return taint_src
        
    Sub.dynamic_call = dynamic_method  # 绑定污染方法
    
    sub = Sub()
    taint_sink(sub.dynamic_call())  # 调用动态绑定的方法


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    polymorphism_003_T(taint_src)

