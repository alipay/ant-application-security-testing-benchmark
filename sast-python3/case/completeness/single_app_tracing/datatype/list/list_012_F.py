# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = 泛型序列
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_012_F
# evaluation information end

import os
from typing import Sequence

def list_012_F(taint_src):
    # 使用 Sequence 注解
    def process_items(items: Sequence[str]) -> None:
        taint_sink(items[1])

    fruits = [taint_src, "banana", "cherry"]
    process_items(fruits)  


def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_012_F(taint_src)
