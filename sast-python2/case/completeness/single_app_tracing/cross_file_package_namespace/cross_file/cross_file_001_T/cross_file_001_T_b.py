# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
# scene introduction = 直接导入
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_001_T/cross_file_001_T_b
# evaluation information end
import os
from cross_file_001_T_a import cross_file_001_T_a


def cross_file_001_T_b(taint_src):
    result = cross_file_001_T_a(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o) 


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    cross_file_001_T_b(taint_src)
