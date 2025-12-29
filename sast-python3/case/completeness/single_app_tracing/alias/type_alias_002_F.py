# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 类型别名
# level = 2
# bind_url = completeness/single_app_tracing/alias/type_alias_002_F
# evaluation information end
import os
from typing import List, Dict

# 定义一个类型别名 PointList 表示包含点坐标的列表
PointList = List[Dict[str, float]]

def type_alias_002_F(taint_src):
    points: PointList = [{'x': taint_src, 'y': 1.0}, {'x': 2.0, 'y': 3.0}]
    taint_sink(points[1]['x'])

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = 10.5
    type_alias_002_F(taint_src)
