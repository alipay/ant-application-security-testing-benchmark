# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = append操作
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_005_T
# evaluation information end
import os


def list_005_T(taint_src):
    s = []
    s.append(taint_src)
    taint_sink(s)


def taint_sink(o):
    os.system(str(o))


if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_005_T(taint_src)

