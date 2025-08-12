# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->别名
# scene introduction = 自定义类型别名
# level = 2+
# bind_url = completeness/single_app_tracing/alias/type_alias_003_T
# evaluation information end
import os
from typing import List

class Point:
    def __init__(self, x: str, y: str):
        self.x = x
        self.y = y

# 定义一个类型别名 Points 表示 Point 对象的列表
Points = List[Point]

def type_alias_003_T(taint_src: str):
    points: Points = [Point(taint_src, "SAFE"), Point("2.0", "3.0")]
    taint_sink(points[0].x)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    type_alias_003_T(taint_src)
