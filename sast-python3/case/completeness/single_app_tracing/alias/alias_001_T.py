# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 别名问题
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_001_T
# evaluation information end
import os


def alias_001_T(taint_src):
    a = {'value': '_'}
    b = a
    b['value'] = taint_src
    taint_sink(a['value'])


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    alias_001_T(taint_src)

