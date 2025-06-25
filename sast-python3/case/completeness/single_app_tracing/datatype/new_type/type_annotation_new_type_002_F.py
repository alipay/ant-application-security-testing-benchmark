# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->名义类型
# scene introduction = 
# level = 2
# bind_url = completeness/single_app_tracing/datatype/new_type/type_annotation_new_type_002_F
# evaluation information end


import os
from typing import NewType

# 基于str类型，用NewType创建新的类型   
# User和Product在类型检查中分别是独立的类型，即使它们的底层类型str同，但互相之间并不通用
User = NewType('User', str)
Product = NewType('Product', str)


def type_annotation_new_type_002_F(taint_src):
    def process_user(user: User , product: Product):
        return product

    x = process_user(taint_src , Product("_"))
    taint_sink(x)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = User("taint_src_value")
    type_annotation_new_type_002_F(taint_src)
