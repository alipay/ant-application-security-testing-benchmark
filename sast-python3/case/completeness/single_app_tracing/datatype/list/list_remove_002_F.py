# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = remove操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_remove_002_F
# evaluation information end

import os

def list_remove_002_F(taint_src):
    # 创建包含干净数据的列表
    lst = ['clean1', taint_src, 'clean3']
    
    # 删除干净元素
    lst.remove(taint_src)
    
    # 传递给sink
    taint_sink(lst)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_remove_002_F(taint_src)