# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->其他->ellipsis
# scene introduction = 切片占位
# level = 2+
# bind_url = completeness/other/ellipsis/ellipsis_001_T
# evaluation information end


# 在 Python 2 中，Ellipsis (...) 主要用于处理多维数组时的切片操作，特别是在 NumPy 库中。
# 它并不能像python3中一样作为一个独立的语句来使用，如果要作为独立的语句使用，请使用pass来替代。
import os
import numpy as np

def ellipsis_001_T(taint_src):
    arr = np.random.randint(taint_src, 10, (3, 3, 3))  # 创建一个 3x3x3 的随机数组

    # 使用 Ellipsis 进行切片操作
    sliced = arr[..., 0]  # 等价于 arr[:, :, 0]
    taint_sink(sliced)


def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = 0
    ellipsis_001_T(taint_src)

