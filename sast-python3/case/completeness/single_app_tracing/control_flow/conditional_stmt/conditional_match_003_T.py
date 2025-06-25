# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
# scene introduction = match_or
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_match_003_T
# evaluation information end


import os

def conditional_match_003_T(taint_src):
    match 2:
        case 1 | 2:
            taint_sink(taint_src)
        case _:
            pass


def taint_sink(o):
    os.system(o)  

if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_match_003_T(taint_src)

