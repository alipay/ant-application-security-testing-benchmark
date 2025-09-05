# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
# scene introduction = 
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/array/array_001_T
# evaluation information end

import os
import array


def array_001_T(taint_src):
    char_array = array.array(u'u', taint_src)  # 每个字符作为独立元素
    s = array.array(u'u', [char_array[0], u'b', u'c'])  
    taint_sink(s)  

def taint_sink(o):
    os.system(u''.join(o))  

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    array_001_T(taint_src)  

