# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 函数参数别名
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_008_F
# evaluation information end
import os


def alias_008_F(taint_src):
    def modify_param(obj):
        obj['value'] = '_'  # 通过参数修改

    a = {'value': taint_src}
    modify_param(a)  # 传递别名
    taint_sink(a['value'])  

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    alias_008_F(taint_src)

