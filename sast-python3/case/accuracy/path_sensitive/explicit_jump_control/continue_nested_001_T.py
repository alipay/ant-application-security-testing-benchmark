# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->跳转语句
# scene introduction = continue-嵌套循环
# level = 4+
# bind_url = accuracy/path_sensitive/explicit_jump_control/continue_nested_001_T
# evaluation information end
import os

def continue_nested_001_T(taint_src):
    res = ""
    for i in range(3):
        for j in range(3):
            if i == 1 and j == 0:
                res = taint_src
                continue  # 跳过内层循环本次迭代，但内层循环继续
            # continue跳过后，i=1, j>0时执行这里
            taint_sink(res)  # 应该检出 - i=1, j>0时res有污点

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    continue_nested_001_T(taint_src)