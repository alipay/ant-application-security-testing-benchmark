# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 列表->二维
# level = 2
# bind_url = accuracy/object_sensitive/collection/list_object_sensitive_004_F
# evaluation information end
import os

def list_object_sensitive_004_F(taint_src):
    s = [[taint_src], [u"b"], u"c"]
    s2 = [[u"a"], [u"b"], u"c"]

    taint_sink(s2)


def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    list_object_sensitive_004_F(taint_src)

