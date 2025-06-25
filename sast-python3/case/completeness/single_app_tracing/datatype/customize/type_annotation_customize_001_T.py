# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->自定义类型注解
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/datatype/customize/type_annotation_customize_001_T
# evaluation information end

import os

# 假设有一个自定义类型MyClass。
class MyClass:
    def __init__(self, value):
        self.value = value  

def type_annotation_customize_001_T(taint_src):
    a: MyClass = MyClass(taint_src) # 使用自定义类型注解，a的值必须是MyClass类的实例
    taint_sink(a.value)

def taint_sink(o):
    os.system(o)

    
if __name__ == "__main__":
    taint_src = "taint_src_value"
    type_annotation_customize_001_T(taint_src)
