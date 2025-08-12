# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 子级目录别名导出
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_015_T/B/cross_module_015_T_b
# evaluation information end

import os
from A.cross_module_015_T_a import exportedVariable as fooVar

def cross_module_015_T_b():
    result = fooVar
    taint_sink(result)


def taint_sink(o):
    os.system(o)
