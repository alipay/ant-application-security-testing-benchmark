# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = pop操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_pop_001_T
# evaluation information end

import os

def list_pop_001_T(taint_src):
    
    # 创建包含污点数据的列表，污点元素在首位
    lst = [taint_src, 'clean1', 'clean2']
    
    # 弹出指定位置的元素（污点元素）
    popped = lst.pop(0)
    
    # 将弹出的元素传递给sink
    taint_sink(popped)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_pop_001_T(taint_src)