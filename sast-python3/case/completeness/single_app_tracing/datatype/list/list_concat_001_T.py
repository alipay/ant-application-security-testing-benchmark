# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = 连接操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_concat_001_T
# evaluation information end

import os

def list_concat_001_T(taint_src):
    
    # 创建包含污点数据的列表
    lst2 = [taint_src, 'clean3']
    
    # 创建初始列表
    lst1 = ['clean1', 'clean2']
    
    # 执行连接操作（+运算符）
    result = lst2 + lst1
    
    # 传递给sink
    taint_sink(result)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_concat_001_T(taint_src)