# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 相对导入-多级导入整个模块
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_037_T/cross_module_037_T_a
# evaluation information end

def source(taint_src):
    return f"{taint_src}_default"

