# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 跨目录默认导出
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_004_F/A/cross_module_004_F_a
# evaluation information end


def defaultExport(taint_src):
    return "{}_default".format(taint_src)


def defaultExport2(taint_src):
    return "default"


__all__ = ['defaultExport2']

