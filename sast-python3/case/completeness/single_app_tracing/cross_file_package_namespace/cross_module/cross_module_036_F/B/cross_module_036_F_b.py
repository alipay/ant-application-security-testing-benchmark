# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 相对导入-导入整个模块
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_036_F/B/cross_module_036_F_b
# evaluation information end

import os
from .. import cross_module_036_F_a

def cross_module_035_T_b(taint_src):
    result = cross_module_036_F_a.source(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    cross_module_035_T_b(taint_src)

