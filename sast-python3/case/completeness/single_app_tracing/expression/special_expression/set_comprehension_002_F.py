# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 集合推导式
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/set_comprehension_002_F
# evaluation information end

import os

def set_comprehension_002_F(taint_src):
    # 使用集合推导式生成一个包含偶数的集合
    s = {x for x in range(5) if x % 2 == 0}  # 只保留偶数
    taint_sink(s)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = 2  
    set_comprehension_002_F(taint_src)

