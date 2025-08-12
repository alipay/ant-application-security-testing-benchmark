# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 列表->del-extend函数
# level = 2+
# bind_url = accuracy/object_sensitive/collection/list_object_sensitive_007_T
# evaluation information end
import os

def list_object_sensitive_007_T(taint_src):
    s = [u"a", u"b"]
    # 扩展污染列表
    s.extend([taint_src, u"d"])

    taint_sink(s)  # 传递污染后的列表


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    list_object_sensitive_007_T(taint_src)

