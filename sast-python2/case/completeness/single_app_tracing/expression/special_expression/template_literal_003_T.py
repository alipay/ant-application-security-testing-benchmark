# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 模板字面量
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/template_literal_003_T
# evaluation information end
import os


def template_literal_003_T(taint_src):
    result = "{}_".format(taint_src)
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    template_literal_003_T(taint_src)

