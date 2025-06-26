# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 别名问题
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_002_F
# evaluation information end
import os


def alias_002_F(taint_src):
    a = {'value':taint_src }
    b = a
    b['value'] = '_'
    taint_sink(a['value'])


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    alias_002_F(taint_src)

