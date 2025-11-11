# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 返回值传递->多返回值解包传递
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_007_T
# evaluation information end
import os

def return_value_passing_007_T(taint_src):
    def get_multiple_values():
        # 函数返回多个值，其中包含污点数据
        return taint_src, 'safe_value', 'another_safe'
    
    # 多返回值解包，第一个值是污点
    tainted_value, safe_value1, safe_value2 = get_multiple_values()
    taint_sink(tainted_value)  # 传递污点值

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    return_value_passing_007_T(taint_src)