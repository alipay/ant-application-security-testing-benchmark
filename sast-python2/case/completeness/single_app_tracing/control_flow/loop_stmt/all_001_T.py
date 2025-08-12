# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = all
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/all_001_T
# evaluation information end
import os

def all_001_T(taint_src):
    arr = [u'a', u'b', taint_src]
    all(taint_sink(item) for item in arr)

def taint_sink(o):
    os.system(o)  
    return True  # 返回 True 来模拟继续检查其他元素的条件



# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    all_001_T(taint_src)
