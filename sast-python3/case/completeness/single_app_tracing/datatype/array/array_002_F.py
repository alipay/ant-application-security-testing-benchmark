# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
# scene introduction = 
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/array/array_002_F
# evaluation information end

import os
import array

def array_002_F(taint_src):
    char_array = array.array('u', taint_src)  # 每个字符作为独立元素
    s = array.array('u', ['a', 'b', 'c'])  
    taint_sink(s)  

def taint_sink(o):
    os.system(''.join(o)) 

if __name__ == "__main__":
    taint_src = "taint_src_value"
    array_002_F(taint_src) 

