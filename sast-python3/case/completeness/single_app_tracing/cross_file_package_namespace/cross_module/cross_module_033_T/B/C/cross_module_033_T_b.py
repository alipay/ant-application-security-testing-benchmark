# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 多级模块相对导入
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_033_T/B/C/cross_module_03_T_b
# evaluation information end

import os
from ...cross_module_033_T_a import source

def cross_module_033_T_b(taint_src):
    result = source(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    cross_module_033_T_b(taint_src)

