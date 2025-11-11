# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
# scene introduction = 动态导入-安全数据调用
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/dynamic_import_002_F/dynamic_import_002_F_a
# evaluation information end

def get_safe_data(taint_src):
    return "safe_data"  # 返回安全数据，不是污点数据