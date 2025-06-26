# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 列表索引->三维交叉
# level = 4+
# bind_url = accuracy/field_sensitive/multidimensional_collection/list_mc_004_F
# evaluation information end
import os



def list_mc_004_F(taint_src):
    s = [[[taint_src, u"x"], [u"y", u"z"]], [[u"a", u"b"], [u"c", u"d"]]]
    taint_sink(s[1][0][0])  # 访问未污染的 "a"


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    list_mc_004_F(taint_src)

