# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
# scene introduction = numpy数组
# level = 2
# bind_url = completeness/single_app_tracing/datatype/array/numpy_array_001_T
# evaluation information end

# 需要安装numpy库来使用此语法创建一个数组  pip install numpy
import os
import numpy as np


def numpy_array_001_T(taint_src):
    arr = np.array([taint_src, 111])
    taint_sink(arr)  


def taint_sink(o):
    os.system(u''.join(o))


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    numpy_array_001_T(taint_src)

