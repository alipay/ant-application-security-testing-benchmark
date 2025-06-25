# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
# scene introduction = 列表索引->二维
# level = 4
# bind_url = accuracy/field_sensitive/multidimensional_collection/list_mc_001_T
# evaluation information end
import os

# 在python中 [] 形式的数据结构叫做列表List，而不是叫数组 或者被称为“动态数组”
# 在python中内置的数组的创建方式是： import array  arr = array.array('i', [1, 2, 3])
# array模块仅提供一维数组，元素类型必须统一（如全为整数或全为浮点数）。
# 另一种创建数组的方法是. NumPy库的数组 import numpy as np     arr_2d = np.array([[1, 2], [3, 4]])
# 此方法可以创建多维数组，但此方法不是python内置方法，需要额外下载NumPy库


def list_mc_001_T(taint_src):
    s = [[taint_src], ["b"], "c"]
    taint_sink(s[0][0])

def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    list_mc_001_T(taint_src)