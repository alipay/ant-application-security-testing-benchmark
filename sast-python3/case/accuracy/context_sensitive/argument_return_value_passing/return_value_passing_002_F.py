# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 返回值传递
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_002_F
# evaluation information end
import os

def return_value_passing_002_F(taint_src):
    def process():
        return '_'
    
    data = process()
    taint_sink(data)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    return_value_passing_002_F(taint_src)

