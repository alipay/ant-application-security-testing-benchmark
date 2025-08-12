# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 集合推导式
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/set_comprehension_001_T
# evaluation information end

import os
# 调整内容
def set_comprehension_001_T(taint_src):
    # 使用集合推导式生成一个包含偶数的集合
    s = {x for x in range(5) if x % taint_src == 0}  # 只保留偶数
    taint_sink(s)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = 2  
    set_comprehension_001_T(taint_src)

