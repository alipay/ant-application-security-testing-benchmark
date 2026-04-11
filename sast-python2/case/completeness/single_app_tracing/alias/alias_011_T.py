# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 函数别名
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_011_T
# evaluation information end
import os


def alias_011_T(taint_src):
    def process(data):
      taint_sink(data)
    
    func = process
    
    func(taint_src)

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    alias_011_T(taint_src)

