# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 动态参数->动态字段名
# level = 3
# bind_url = accuracy/field_sensitive/class/dynamic_field_001_T
# evaluation information end
import os

def dynamic_field_001_T(taint_src):
    class DynamicClass:
        def __init__(self, taint_src):
            # 使用setattr动态设置字段
            setattr(self, 'dynamic_field', taint_src)
            self.normal_field = '_'
    
    obj = DynamicClass(taint_src)
    # 通过动态字段名访问
    taint_sink(obj.dynamic_field)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    dynamic_field_001_T(taint_src)