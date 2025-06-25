# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
# scene introduction = 泛型类
# level = 2
# bind_url = completeness/single_app_tracing/class/simple_object/simple_object_006_F
# evaluation information end
import os
from typing import Generic, TypeVar

# 定义泛型类型变量
T = TypeVar('T')

#通过 Generic，你可以创建可以接受类型参数的类，这些类型参数在类实例化时被具体化。
class Box(Generic[T]):
    def __init__(self, content: T):
        self.content = content

    def get_content(self) -> T:
        return self.content


def simple_object_006_F(taint_src):
    # 使用泛型类
    int_box = Box("_")
    taint_sink(int_box.get_content())


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    simple_object_006_F(taint_src)
