# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 路径长度
# level = 3
# bind_url = accuracy/field_sensitive/class/field_len_002_F
# evaluation information end
import os

def field_len_002_F(taint_src):
    class A(object):
        def __init__(self, taint_src):
            self.b = B(taint_src)  # 将参数传递给下一层类

    class B(object):
        def __init__(self, taint_src):
            self.c = C(taint_src)  # 继续传递参数到 C

    class C(object):
        def __init__(self, taint_src):
            self.data = taint_src   # 使用参数赋值给 data
            self.sani = u'_'         # 固定值 '_'

    # 创建实例时传入 taint_src
    a = A(taint_src)
    taint_sink(a.b.c.sani)        # 传递固定值 '_' 给 sink


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    field_len_002_F(taint_src)

