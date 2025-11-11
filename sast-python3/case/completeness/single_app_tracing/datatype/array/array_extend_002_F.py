# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
# scene introduction = extend操作
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/array/array_extend_002_F
# evaluation information end

import os
import array

def array_extend_002_F(taint_src):
    # 创建初始数组
    arr = array.array('u', ['a', 'b'])
    
    clean_arr = array.array('u', ['x'])
    
    # 执行extend操作
    arr.extend(clean_arr)
    
    # 传递给sink
    taint_sink(arr)

def taint_sink(o):
    os.system(''.join(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    array_extend_002_F(taint_src)