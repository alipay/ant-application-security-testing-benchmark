# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 返回值传递->多返回值解包传递
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_008_F
# evaluation information end
import os

def return_value_passing_008_F(taint_src):
    def get_multiple_values():
        # 函数返回多个值，但都不包含污点数据
        return 'safe_value1', 'safe_value2', 'safe_value3'
    
    # 多返回值解包，所有值都是安全的
    safe_value1, safe_value2, safe_value3 = get_multiple_values()
    taint_sink(safe_value1)  # 传递安全值，不应检出漏洞

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    return_value_passing_008_F(taint_src)