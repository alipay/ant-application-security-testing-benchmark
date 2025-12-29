# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->其他->ellipsis
# scene introduction = 占位符
# level = 2
# bind_url = completeness/other/ellipsis/ellipsis_001_T
# evaluation information end


import os


def ellipsis_001_T(taint_src):
    ...  # 使用 Ellipsis 作为占位符
    taint_sink(taint_src)


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    ellipsis_001_T(taint_src)

