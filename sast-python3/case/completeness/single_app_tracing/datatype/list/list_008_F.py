# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->列表
# scene introduction = 泛型容器类型
# level = 2
# bind_url = completeness/single_app_tracing/datatype/list/list_008_F
# evaluation information end

import os
from typing import List
#泛型类型是一种特殊的类型注解，它允许你在定义容器时指定容器内元素的类型。

def list_008_F(taint_src):
    # 泛型类型注解,list容器中定义的内容必须是str
    l: List[str] = [taint_src,"_"]  
    taint_sink(l[1])

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_008_F(taint_src)
