# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
# scene introduction = datetime库
# level = 2
# bind_url = completeness/single_app_tracing/function_call/library_function/datetime_001_T
# evaluation information end


import os
from datetime import datetime

def datetime_001_T(taint_src):
    def process(time_str):
        dt = datetime.strptime(time_str, "%Y-%m-%d %H:%M:%S")
        taint_sink(dt) 

    process(f"2025-03-25 {taint_src}")  # 输入格式化的日期时间字符串

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "12:11:29"
    datetime_001_T(taint_src)

