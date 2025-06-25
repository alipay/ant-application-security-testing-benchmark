# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
# scene introduction = 映射->域敏感->map类库函数keys()
# level = 3
# bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_009_F
# evaluation information end
import os

def map_field_sensitive_009_F(taint_src):
    my_map = {}  
    my_map['key1'] = taint_src  # 键 'key1' 是干净的，但值被污染
    my_map['key2'] = 'value'      # 键 'key2' 是干净的，值干净
    taint_sink(list(my_map.keys()))  # 传递所有键的列表（键均为干净）


def taint_sink(o):
    os.system(str(o))


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    map_field_sensitive_009_F(taint_src)

