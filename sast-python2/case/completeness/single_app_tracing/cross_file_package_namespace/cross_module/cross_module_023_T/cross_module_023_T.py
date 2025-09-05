# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
# scene introduction = 在init文件中import—双层嵌套
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_023_T/cross_module_023_T
# evaluation information end

import os
import A

def cross_module_023_T(taint_src):
    result = A.function_a(taint_src)  
    taint_sink(result)

def taint_sink(o):
    os.system(o) 

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    cross_module_023_T(taint_src)