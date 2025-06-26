# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 数组/集合->集合对象
# level = 2
# bind_url = accuracy/object_sensitive/collection/set_object_sensitive_002_F
# evaluation information end
import os

def set_object_sensitive_002_F(taint_src):
    polluted_set = set(taint_src)  
    s = set('_')  

    # 传递污染集合到 sink 函数
    taint_sink(s)


def taint_sink(o):
    os.system(str(o))

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    set_object_sensitive_002_F(taint_src)

