# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = insert操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_insert_002_F
# evaluation information end

import os

def list_insert_002_F(taint_src):
    # 创建初始列表
    lst = ['clean1', 'clean2']
    
    # 在指定位置插入干净数据
    lst.insert(0, 'clean3')
    
    # 传递给sink
    taint_sink(lst)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_insert_002_F(taint_src)