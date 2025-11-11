# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->上下文敏感分析->参数/返回值传递
# scene introduction = 返回值传递->迭代器返回值传递
# level = 2
# bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_006_F
# evaluation information end
import os

def return_value_passing_006_F(taint_src):
    def create_iterator():
        # 创建只包含安全值的迭代器
        return iter(['safe_value', 'another_value', 'third_value'])
    
    iterator = create_iterator()  # 返回迭代器对象
    first_item = next(iterator)   # 获取迭代器的第一个元素
    taint_sink(first_item)        # 传递安全值

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    return_value_passing_006_F(taint_src)