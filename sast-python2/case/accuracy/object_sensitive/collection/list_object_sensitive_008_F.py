# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 列表->del-extend函数
# level = 2+
# bind_url = accuracy/object_sensitive/collection/list_object_sensitive_008_F
# evaluation information end
import os


def list_object_sensitive_008_F(taint_src):
    s = [taint_src, u"b"]
    # 扩展干净列表
    del s[:]  # 使用 Python2 支持的方式清空列表
    s.extend([u"_", u"d"])

    taint_sink(s)  # 传递被覆盖后的列表


def taint_sink(o):
    os.system(unicode(o))


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    list_object_sensitive_008_F(taint_src)

