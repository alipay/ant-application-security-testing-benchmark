# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
# scene introduction = 布尔转换
# level = 2
# bind_url = completeness/single_app_tracing/expression/type_cast/bool_conversion_002_F
# evaluation information end
import os


def bool_conversion_002_F(taint_src):
    result = bool("")  
    taint_sink(result)
    

def taint_sink(o):
    os.system(str(o))


if __name__ == "__main__":
    taint_src = "True"
    bool_conversion_002_F(taint_src)  # 输出True

