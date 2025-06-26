# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->条件表达式
# scene introduction = 逻辑与
# level = 2
# bind_url = completeness/single_app_tracing/expression/conditional_expression/logical_and_001_T
# evaluation information end
import os

# ?
def logical_and_001_T(taint_src):
     result = taint_src.startswith(u"t") and taint_src.endswith(u"e")  # 污点通过AND条件传递
     taint_sink(result) 

def taint_sink(o):
    os.system(unicode(o))


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    logical_and_001_T(taint_src)

