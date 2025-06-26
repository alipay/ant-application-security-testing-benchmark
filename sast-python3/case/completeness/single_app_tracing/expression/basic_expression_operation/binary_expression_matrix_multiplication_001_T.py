# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
# scene introduction = 二元运算->矩阵乘法
# level = 2
# bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_matrix_multiplication_001_T
# evaluation information end

# 矩阵乘法 Python 3.5 开始引入的功能 需要安装numpy库来使用此语法  pip install numpy
import os
import numpy as np


def binary_expression_matrix_multiplication_001_T(taint_src):
    # 初始化两个矩阵
    a = np.array([[1, 2], [3, 4]])
    b = np.array([[taint_src, 1], [2, 2]])  # 将污染源作为矩阵的一个元素
    
    # 执行矩阵乘法
    result = a @ b
    
    taint_sink(result)


def taint_sink(o):
    os.system(str(o)) 


if __name__ == '__main__':
    taint_src = 7  # 示例输入，应该是一个数值，以便可以参与矩阵运算
    binary_expression_matrix_multiplication_001_T(taint_src)
