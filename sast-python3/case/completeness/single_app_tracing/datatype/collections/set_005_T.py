# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
# scene introduction = 交集-并集
# level = 2
# bind_url = completeness/single_app_tracing/datatype/collections/set_005_T
# evaluation information end
import os

def set_005_T(taint_src):
    set1 = {'safe',taint_src}
    set2 = {taint_src, 'b'}
    result = set1.union(set2)  # 并集包含污点
    taint_sink(result)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    set_005_T(taint_src)

