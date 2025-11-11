# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
# scene introduction = 动态参数->动态字段名
# level = 3
# bind_url = accuracy/field_sensitive/class/dynamic_field_002_F
# evaluation information end
import os

def dynamic_field_002_F(taint_src):
    class DynamicClass:
        def __init__(self, taint_src):
            # 使用setattr动态设置字段为安全值
            setattr(self, 'dynamic_field', '_')
            self.tainted_field = taint_src
    
    obj = DynamicClass(taint_src)
    # 访问的是安全的动态字段，而非污染的字段
    taint_sink(obj.dynamic_field)  # 传递安全值，不应检出漏洞


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    dynamic_field_002_F(taint_src)