# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 子级目录默认导出
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_011_T/cross_module_011_T
# evaluation information end

from B.cross_module_011_T_b import cross_module_011_T_b


if __name__ == '__main__':
    taint_src = "taint_src_value"
    cross_module_011_T_b(taint_src)

