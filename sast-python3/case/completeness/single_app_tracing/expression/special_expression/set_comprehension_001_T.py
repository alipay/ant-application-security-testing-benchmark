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
    # 使用集合推导式，将污点源 taint_src 直接作为集合中的元素（突出集合推导式直接包含污点源）
    s = {taint_src for _ in range(1)}  # 集合推导式中直接将污点源作为元素输出
    taint_sink(s)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = 2  
    set_comprehension_001_T(taint_src)

