# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 列表元素别名
# level = 2
# bind_url = completeness/single_app_tracing/alias/alias_006_F
# evaluation information end
import os


def alias_006_F(taint_src):
    a = [taint_src, '_']
    b = a  # 别名
    b[0] = '_'  # 修改列表元素
    taint_sink(a[0])  

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    alias_006_F(taint_src)

