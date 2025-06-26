# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
# scene introduction = 默认导出
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_003_T/cross_file_003_T_a
# evaluation information end


def defaultExport(taint_src):
    return f"{taint_src}_default"


def defaultExport2(taint_src):
    return f"default"


__all__ = ['defaultExport']
