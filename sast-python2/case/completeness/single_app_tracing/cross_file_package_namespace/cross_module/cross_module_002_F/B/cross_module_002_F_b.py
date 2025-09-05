# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 跨目录直接导入
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_002_F/B/cross_module_002_F_b
# evaluation information end

import sys
import os
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
from A.cross_module_002_F_a import cross_module_002_F_a


def cross_module_002_F_b(taint_src):
    result = cross_module_002_F_a()
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == u'__main__':
    taint_src = u"taint_src_value"
    cross_module_002_F_b(taint_src)

