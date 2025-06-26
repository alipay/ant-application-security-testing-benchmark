# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
# scene introduction = json库
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/library_function/json_002_F
# evaluation information end
import os
import json


def json_002_F(taint_src):
    def process(arg):
        obj = json.loads(arg)
        taint_sink(obj["key"])
        
    process('{"key": "_"}')


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = '{"key": taint_src_value}'
    json_002_F(taint_src)

