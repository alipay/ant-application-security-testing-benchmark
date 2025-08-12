# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 在init文件中import—双层嵌套
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_021_T/A/B/cross_module_021_T_b
# evaluation information end


def function_b(taint_src):
    return taint_src