# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = insert操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_insert_001_T
# evaluation information end

import os

def list_insert_001_T(taint_src):
    
    # 创建初始列表
    lst = ['clean1', 'clean2']
    
    # 在指定位置插入数据
    lst.insert(0, taint_src)
    
    # 传递给sink
    taint_sink(lst)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_insert_001_T(taint_src)