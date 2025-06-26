# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 路径长度
# level = 3+
# bind_url = accuracy/field_sensitive/class/field_len_006_T
# evaluation information end
import os

def field_len_006_T(taint_src):
    class A:
        def __init__(self, taint_src):
            self.b = B(taint_src)  # 传递参数到 B

    class B:
        def __init__(self, taint_src):
            self.c = C(taint_src)  # 传递参数到 C

    class C:
        def __init__(self, taint_src):
            self.d = D(taint_src)  # 传递参数到 D

    class D:
        def __init__(self, taint_src):
            self.e = E(taint_src)  # 传递参数到 E

    class E:
        def __init__(self, taint_src):
            self.f = F(taint_src)  # 传递参数到 F

    class F:
        def __init__(self, taint_src):
            self.g = G(taint_src)  # 传递参数到 G

    class G:
        def __init__(self, taint_src):
            self.h = H(taint_src)  # 传递参数到 H

    class H:
        def __init__(self, taint_src):
            self.i = I(taint_src)  # 传递参数到 I

    class I:
        def __init__(self, taint_src):
            self.j = J(taint_src)  # 传递参数到 J

    class J:
        def __init__(self, taint_src):
            self.k = K(taint_src)  # 传递参数到 K

    class K:
        def __init__(self, taint_src):
            self.l = L(taint_src)  # 传递参数到 L

    class L:
        def __init__(self, taint_src):
            self.data = taint_src   # 污染数据
            self.sani = '_'         # 固定值 '_'

    # 创建实例链
    a = A(taint_src)
    taint_sink(a.b.c.d.e.f.g.h.i.j.k.l.sani)  # 完整路径访问 a.b.c.d.e.f.g.h.i.j.k.l.sani


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    field_len_006_T(taint_src)

