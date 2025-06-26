# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
# scene introduction = matchMapping
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_matchMapping_002_F
# evaluation information end


import os

def conditional_matchMapping_002_F(taint_src):
    data = {"key1": "aaa", "key2": "suffix"}
    match data:
        case {"key1": x, "key2": y}:  # 使用 MatchMapping 匹配固定键值对
            taint_sink(x)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_matchMapping_002_F(taint_src)
