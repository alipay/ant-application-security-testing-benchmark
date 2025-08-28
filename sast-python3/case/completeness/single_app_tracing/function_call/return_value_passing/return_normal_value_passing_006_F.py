# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
# scene introduction = 类型注解
# level = 2
# bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_normal_value_passing_006_F
# evaluation information end

import os

def return_normal_value_passing_006_F(taint_src):
    #name形参规定必须接收一个字符串 函数返回值规定必须是一个字符串
    def greeting(name: str) -> str:
        return 'Hello' + "_"
    
    x = greeting(taint_src)
    taint_sink(x)


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    return_normal_value_passing_006_F(taint_src)
