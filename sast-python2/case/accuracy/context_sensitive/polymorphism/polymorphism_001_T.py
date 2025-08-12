# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->多态
# scene introduction = 不同子类实现相同方法
# level = 2
# bind_url = accuracy/context_sensitive/polymorphism/polymorphism_001_T
# evaluation information end
import os

# 作用：通过多态性（不同子类实现相同方法）测试污点传播路径。
def polymorphism_001_T(taint_src):
    class Base(object):
        pass  # 父类无需实现 call() 方法，由子类覆盖

    class Sub1(Base):
        def call(self):
            return taint_src  # 子类1返回污染源

    class Sub2(Base):
        def call(self):
            return u'_'  # 子类2返回固定值

    sub = Sub1()  # 实例化子类1
    taint_sink(sub.call())  # 调用多态方法


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    polymorphism_001_T(taint_src)

