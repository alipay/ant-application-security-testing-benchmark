# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
# scene introduction = 字符串创建
# level = 2
# bind_url = completeness/single_app_tracing/datatype/string/string_type_002_F
# date = 2026-01-06 05:56:38
# evaluation information end
import os

def string_type_002_F(taint_src):
    # 场景特点：创建安全字符串对象，与输入无关
    data = u"safe_value"
    taint_sink(data)

def taint_sink(o):
    os.system(str(o))

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    string_type_002_F(taint_src)