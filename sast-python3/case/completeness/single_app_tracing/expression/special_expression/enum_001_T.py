# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 枚举
# level = 2
# bind_url = completeness/single_app_tracing/expression/special_expression/enum_001_T
# evaluation information end

from enum import Enum
import os


def enum_001_T(taint_src):

    #把taint_src的值当成枚举
    class Color(Enum):
      RED = 1
      GREEN = 2
      BLUE = taint_src

    color_map = {"red": Color.RED, "green": Color.GREEN, "blue": Color.BLUE}
    taint_sink(color_map["blue"].value)


def taint_sink(o):
    os.system(str(o))


if __name__ == '__main__':
    taint_src = "taint_src_value"  
    enum_001_T(taint_src)
