# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
# scene introduction = 星号匹配->字典嵌套
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_matchStar_004_F
# evaluation information end


import os


def conditional_matchStar_004_F(taint_src):
    data = {"key": "value1", "extra1": "aaa", "extra2": "value2"}
    match data:
        case {"key": value, **rest}:  # 使用 MatchStar 捕获字典剩余项
            taint_sink(rest.values())

def taint_sink(o):
    os.system("".join(o))


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_matchStar_004_F(taint_src)
