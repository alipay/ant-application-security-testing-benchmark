# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
# scene introduction = 变量
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_005_T/cross_file_005_T_b
# evaluation information end

import os
from cross_file_005_T_a import exportedVariable


def cross_file_005_T_b():
    result = exportedVariable
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    cross_file_005_T_b()

