# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
# scene introduction = set元素删除操作
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/collections/set_remove_002_F
# evaluation information end

import os

def set_remove_002_F(taint_src):
    # 创建只包含干净数据的set
    s = {taint_src, 'clean2', 'clean3'}
    
    # 从set中删除干净元素
    s.remove(taint_src)
    
    # 将删除后的set传递给sink
    taint_sink(s)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    set_remove_002_F(taint_src)