# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 绝对导入
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_040_F/A/cross_module_040_F_a
# evaluation information end

import os
import A.B.cross_module_040_F_b

def cross_module_040_F_a(taint_src):
    result = A.B.cross_module_040_F_b.source(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    cross_module_040_F_a(taint_src)

