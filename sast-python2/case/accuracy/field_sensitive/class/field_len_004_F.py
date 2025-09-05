# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 路径长度
# level = 3+
# bind_url = accuracy/field_sensitive/class/field_len_004_F
# evaluation information end
import os

def field_len_004_F(taint_src):
    class A(object):
        def __init__(self, taint_src):
            self.b = B(taint_src)  # 传递参数到 B

    class B(object):
        def __init__(self, taint_src):
            self.c = C(taint_src)  # 传递参数到 C

    class C(object):
        def __init__(self, taint_src):
            self.d = D(taint_src)  # 传递参数到 D

    class D(object):
        def __init__(self, taint_src):
            self.e = E(taint_src)  # 传递参数到 E

    class E(object):
        def __init__(self, taint_src):
            self.f = F(taint_src)  # 传递参数到 F

    class F(object):
        def __init__(self, taint_src):
            self.data = taint_src   # 污染数据
            self.sani = u'_'         # 固定值 '_'

    # 创建实例链
    a = A(taint_src)
    s = a.b.c                     # 中间变量
    s1 = s.d.e.f.sani            # 完整路径访问 a.b.c.d.e.f.sani
    taint_sink(s1)             # 传递固定值 '_' 给 sink


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    field_len_004_F(taint_src)

