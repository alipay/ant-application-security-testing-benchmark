# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 模板字面量
# level = 2
# bind_url = completeness/single_app_tracing/expression/special_expression/template_literal_004_F
# evaluation information end
import os


def template_literal_004_F(taint_src):
    result = f"{taint_src}_"
    taint_sink('_')


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    template_literal_004_F(taint_src)

