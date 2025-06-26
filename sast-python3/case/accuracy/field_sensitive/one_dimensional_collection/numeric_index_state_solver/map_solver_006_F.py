# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值非数字的场景，能够区分不同索引上特定元素的状态（需要求解）
# scene introduction = 字典->字典索引->需求解
# level = 4
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_solver/map_solver_006_F
# evaluation information end
import os



def map_solver_006_F(taint_src):
    d = {
        0: "a",
        1: "b",
        2: taint_src,  # 将污染源放在键为2的位置
    }
  
    taint_sink(d[1+0])  # 访问键为2的值并传给taint_sink函数


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_solver_006_F(taint_src)  # 调用函数并传入字符串作为污染源


