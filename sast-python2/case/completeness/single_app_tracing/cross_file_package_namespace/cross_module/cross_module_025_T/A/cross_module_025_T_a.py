# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 跨目录导出—双层嵌套
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_025_T/A/cross_module_025_T_a
# evaluation information end

from A.C.cross_module_025_T_c import cross_module_025_T_c

def cross_module_025_T_a(taint_src):
    return cross_module_025_T_c(taint_src)
