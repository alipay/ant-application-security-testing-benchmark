# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 数组/集合->数组对象
# level = 2+
# bind_url = accuracy/object_sensitive/collection/array_object_sensitive_004_F
# evaluation information end


import os
import array

def array_object_sensitive_004_F(taint_src):
    s = array.array('u')  
    char_array = array.array('u', taint_src)  # 每个字符作为独立元素
    s.append(char_array[0])  # 使用 append 方法代替 push
    
    s2 = array.array('u')  
    s2.append('a')  
    
    taint_sink(s2)  


def taint_sink(o):
    os.system(''.join(o))


if __name__ == "__main__":
    taint_src = "taint_src_value"
    array_object_sensitive_004_F(taint_src)  

