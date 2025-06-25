# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
# scene introduction = 多维切片
# level = 2
# bind_url = completeness/single_app_tracing/datatype/array/extslice_002_F
# evaluation information end


#原生的list、array等不支持多维切片的语法 需要额外下载numpy库
import os
import numpy as np

def extslice_002_F(taint_src):
    def process():       
        arr = np.array([[1, 2, taint_src], [4, 5, 6]])
        # 使用ExtSlice来执行多维切片操作，并将可能被污染的数据传递给sink
        taint_sink(arr[0:2, 2][1])  # 假设我们试图从数组中提取可能存在污染的数据

    process()  

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"  
    extslice_002_F(taint_src)
