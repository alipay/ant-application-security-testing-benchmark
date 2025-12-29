# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
# scene introduction = 差集操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/collections/set_007_T
# evaluation information end
import os

def set_007_T(taint_src):
    set1 = {taint_src, 'a'}
    set2 = {'a', 'b'}
    # 计算 set1 和 set2 的差集，即 set1 中有但 set2 中没有的元素
    result = set1 - set2  
    taint_sink(result)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    set_007_T(taint_src)

