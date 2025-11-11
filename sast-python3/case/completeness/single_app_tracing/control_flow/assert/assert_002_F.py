# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->断言
# scene introduction = 验证输入值
# level = 2+
# bind_url = completeness/single_app_tracing/control_flow/assert/assert_002_F
# evaluation information end


import os
import numpy as np


def assert_002_F(taint_src):
    # 创建一个 NumPy 数组，包含 taint_src 和 "SAFE"
    arr = np.array([taint_src, "SAFE"])

    # 使用 assert 确保数组的第一个元素不是空字符串
    assert arr[1] != "", "The first element should not be an empty string."

    taint_sink(arr[1])


def taint_sink(o):
    # 使用 os.system 执行命令
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    assert_002_F(taint_src)
