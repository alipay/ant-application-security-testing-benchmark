# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->跳转语句
# scene introduction = continue-嵌套循环
# level = 4+
# bind_url = accuracy/path_sensitive/explicit_jump_control/continue_nested_002_F
# evaluation information end
import os

def continue_nested_002_F(taint_src):
    res = ""
    for i in range(3):
        for j in range(3):
            if i == 1 and j == 0:
                res = taint_src  # 设置污点数据
                continue  # 跳过内层循环本次迭代
            # continue跳过后执行这里，但传递安全数据
            safe_data = "safe_value"
            taint_sink(safe_data)  # 不应检出 - 传递安全数据

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    continue_nested_002_F(taint_src)