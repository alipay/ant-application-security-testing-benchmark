# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
# scene introduction = 列表->clear-extend函数
# level = 2
# bind_url = accuracy/object_sensitive/collection/list_object_sensitive_008_F
# evaluation information end
import os


def list_object_sensitive_008_F(taint_src):
    s = [taint_src, "b"]
    # 扩展干净列表
    s.clear()
    s.extend(["_", "d"])

    taint_sink(s)  # 传递被覆盖后的列表


def taint_sink(o):
    os.system(str(o))


if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_object_sensitive_008_F(taint_src)

