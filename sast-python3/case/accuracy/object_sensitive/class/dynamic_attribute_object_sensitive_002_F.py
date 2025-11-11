# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象
# scene introduction = 接口/类->动态属性对象
# level = 2
# bind_url = accuracy/object_sensitive/class/dynamic_attribute_object_sensitive_002_F
# evaluation information end
# 区分"动态属性对象"，动态属性赋值
import os


def dynamic_attribute_object_sensitive_002_F(taint_src):
    class DynamicObject:
        def __init__(self, name):
            self.name = name  # 只初始化基础属性

    # 创建对象并动态添加安全属性
    obj = DynamicObject("test_obj")
    obj.dynamic_data = "_"  # 动态添加安全属性
    
    # 直接传递对象给sink
    taint_sink(obj)


def taint_sink(o):
    # 在sink函数内部访问动态属性
    os.system(o.dynamic_data)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    dynamic_attribute_object_sensitive_002_F(taint_src)
