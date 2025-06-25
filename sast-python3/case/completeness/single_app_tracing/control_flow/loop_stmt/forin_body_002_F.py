# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
# scene introduction = forin_body
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/loop_stmt/forin_body_002_F
# evaluation information end
import os


def forin_body_002_F(taint_src):
    obj = {'key1': 'value1', 'key2': 'value2'}
    res = ''

    for key in obj:
        res += obj[key]

    taint_sink(res)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    forin_body_002_F(taint_src)

