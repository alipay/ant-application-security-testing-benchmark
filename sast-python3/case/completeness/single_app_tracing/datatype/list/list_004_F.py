# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = 二维
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_004_F
# evaluation information end
import os


def list_004_F(taint_src):
    s2 = [['_'], ['b'], 'c']
    taint_sink(s2)


def taint_sink(o):
    os.system(str(o))


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_004_F(taint_src)

