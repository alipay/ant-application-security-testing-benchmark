# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 子级目录导出—双层嵌套
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_028_F/cross_module_028_F
# evaluation information end

from A.cross_module_028_F_a import cross_module_028_F_a

def cross_module_028_F(taint_src):
    cross_module_028_F_a(taint_src)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    cross_module_028_F(taint_src)

