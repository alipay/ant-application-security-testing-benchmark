# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 子级目录默认导出
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_012_F/B/cross_module_012_F_b
# evaluation information end

import os
from A.cross_module_012_F_a import *


def cross_module_012_F_b(taint_src):
    result = defaultExport2()
    taint_sink(result)


def taint_sink(o):
    os.system(o)
