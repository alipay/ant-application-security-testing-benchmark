# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
# scene introduction = 泛型映射
# level = 2
# bind_url = completeness/single_app_tracing/datatype/map/map_009_T
# evaluation information end

import os
from typing import Mapping

def process_config(config: Mapping[str, str]) -> None:
    taint_sink(config)

def map_009_T(taint_src):
    # 使用 Mapping 注解
    settings = {"name": taint_src, "language": "en", "font_size": "14"}
    process_config(settings) 

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"  
    map_009_T(taint_src)
