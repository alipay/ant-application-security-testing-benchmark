# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 子级目录直接导入
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_010_F/cross_module_010_F
# evaluation information end

from B.cross_module_010_F_b import cross_module_010_F_b


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    cross_module_010_F_b(taint_src)

