# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
# scene introduction = 字典/映射（Map）对象
# level = 2+
# bind_url = completeness/single_app_tracing/datatype/map/map_008_F
# evaluation information end
import os


def map_008_F(taint_src):
     # 初始化字典
    m = {"key": "_","src":taint_src}
    # 删除污染值
    del m['src'] 
    
    taint_sink(m)  # 传递更新后的字典


def taint_sink(o):
    os.system(str(o))


if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_008_F(taint_src)

