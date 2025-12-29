# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->其他->忽略类型检查
# scene introduction = 忽略类型检查
# level = 2
# bind_url = completeness/other/typeignore/typeignore_002_F
# evaluation information end

# 忽略类型检查的语法就是一行注释： # type: ignore
import os

def typeignore_002_F(taint_src):
    # 直接将传入的值传递给sink，模拟忽略类型检查的安全问题
    taint_sink("_")  # type: ignore

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value" 
    typeignore_002_F(taint_src)
