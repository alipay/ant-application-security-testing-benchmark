# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->其他->ellipsis
# scene introduction = 切片占位
# level = 2+
# bind_url = completeness/other/ellipsis/ellipsis_002_F
# evaluation information end


import os
import numpy as np

def ellipsis_002_F(taint_src):
    arr = np.random.randint(0, 10, (3, 3, 3))  # 创建一个 3x3x3 的随机数组

    # 使用 Ellipsis 进行切片操作
    sliced = arr[..., 0]  # 等价于 arr[:, :, 0]
    taint_sink(sliced)


def taint_sink(o):
    os.system(unicode(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    ellipsis_002_F(taint_src)

