# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 列表->append函数
# level = 2
# bind_url = accuracy/object_sensitive/collection/list_object_sensitive_006_F
# evaluation information end
import os

def list_object_sensitive_006_F(taint_src):
    s = []
    s.append(taint_src)

    s2 = []
    s2.append("a")

    taint_sink(s2)


def taint_sink(o):
    os.system(str(o))

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_object_sensitive_006_F(taint_src)

