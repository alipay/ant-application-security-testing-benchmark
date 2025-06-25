# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 字典->剩余参数
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_012_T
# evaluation information end
import os

# 字典键的引号：Python 要求字符串键必须用引号包裹
def map_field_sensitive_012_T(taint_src):
    params = {
        'name': 'Tony',
        'age': '12',
        'id': taint_src,
    }
    # 使用字典解包（展开运算符的等效操作）创建新字典并访问 id 属性
    taint_sink({**params}['id'])  # 展开 params 并取 id 属性


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_field_sensitive_012_T(taint_src)

