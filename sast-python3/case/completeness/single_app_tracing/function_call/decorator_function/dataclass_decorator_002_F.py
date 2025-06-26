# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = dataclass
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/decorator_function/dataclass_decorator_002_F
# evaluation information end

from dataclasses import dataclass
import os


@dataclass
class Product:
    name: str
    price: float


def dataclass_decorator_002_F(taint_src):
    # 使用 taint_src 初始化 Product 实例
    product = Product(name=taint_src, price=9.99)
    
    taint_sink(product.price)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"
    dataclass_decorator_002_F(taint_src)