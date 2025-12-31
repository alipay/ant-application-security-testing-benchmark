# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 相对导入-多级导入整个模块
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_038_F/A/B/C/D/cross_module_038_F_d
# evaluation information end

import os
from .... import cross_module_038_F_a

def cross_module_038_F_d(taint_src):
    result = cross_module_038_F_a.cross_module_038_F_a(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    cross_module_038_F_d(taint_src)

