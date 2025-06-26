# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
# scene introduction = 泛型类
# level = 2
# bind_url = completeness/single_app_tracing/class/simple_object/simple_object_005_T
# evaluation information end
import os
from typing import Generic, TypeVar

# 定义泛型类型变量
T = TypeVar('T')

class Box(Generic[T]):
    def __init__(self, content: T):
        self.content = content

    def get_content(self) -> T:
        return self.content


def simple_object_005_T(taint_src):
    # 使用泛型类
    int_box = Box(taint_src)
    taint_sink(int_box.get_content())


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    simple_object_005_T(taint_src)
