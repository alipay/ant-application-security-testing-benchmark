# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
# scene introduction = 动态导入-函数调用
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/dynamic_import_001_T/dynamic_import_001_T_a
# evaluation information end

def get_taint_data(taint_src):
    return f"dynamic_{taint_src}"