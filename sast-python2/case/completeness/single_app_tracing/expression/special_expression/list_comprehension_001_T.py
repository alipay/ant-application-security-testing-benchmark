# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 列表推导式
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/list_comprehension_001_T
# evaluation information end

import os

def list_comprehension_001_T(taint_src):
    # 简化的列表推导式，直接使用污染源作为输出的一部分
    result = [x for x in (taint_src, u"_")]
    taint_sink(result[0])

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"  # 固定字符串"taint_src_value"作为source点流入
    list_comprehension_001_T(taint_src)
