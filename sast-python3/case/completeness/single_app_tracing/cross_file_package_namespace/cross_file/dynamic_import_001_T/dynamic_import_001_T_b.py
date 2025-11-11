# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
# scene introduction = 动态导入-函数调用
# level = 2
# bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/dynamic_import_001_T/dynamic_import_001_T_b
# evaluation information end

import os

def dynamic_import_001_T_b(taint_src):
    # 动态导入模块
    module_name = "dynamic_import_001_T_a"
    imported_module = __import__(module_name)
    
    # 调用动态导入模块中的函数
    result = imported_module.get_taint_data(taint_src)
    
    # 验证动态导入后的数据传递
    taint_sink(result)

def taint_sink(o):
    os.system(str(o))

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    dynamic_import_001_T_b(taint_src)