# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 返回值传递
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_001_T
# evaluation information end
import os

def return_value_passing_001_T(taint_src):
    def process():
        # 返回污点源
        return taint_src

    
    data = process()
    taint_sink(data)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    return_value_passing_001_T(taint_src)

