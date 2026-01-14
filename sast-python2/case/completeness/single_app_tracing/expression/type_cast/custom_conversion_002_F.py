# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
# scene introduction = 自定义转换
# level = 2
# bind_url = completeness/single_app_tracing/expression/type_cast/custom_conversion_002_F
# evaluation information end
import os


class CustomConversion(object):
    def __init__(self, data):
        self.data = data

    # 这里自定义了字符串的转换方式
    def __str__(self):
        return "safe_value"


def custom_conversion_002_F(taint_src):
    obj = CustomConversion(taint_src)
    taint_sink(str(obj))


def taint_sink(o):
    os.system(o)


if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    custom_conversion_002_F(taint_src)
