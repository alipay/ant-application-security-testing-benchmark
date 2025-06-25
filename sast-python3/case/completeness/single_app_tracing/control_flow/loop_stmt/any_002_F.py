# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = any
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/any_002_F
# evaluation information end
import os


def any_002_F(taint_src):
    arr = ['a', 'b', 'c']
    any(taint_sink(item) for item in arr)

def taint_sink(o):
    os.system(o)  
    return False  # 返回 False 来模拟继续检查其他元素的条件


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    any_002_F(taint_src)


