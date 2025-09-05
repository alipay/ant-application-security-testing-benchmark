# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
# scene introduction = match_sequence
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_match_008_F
# evaluation information end

import os


def conditional_match_008_F(taint_src):
    value =  (5, 6, 7, 8)
    match value:
        case (a, b, *rest) as full_tuple:
            taint_sink(full_tuple)
        case _ as default:
            taint_sink(default)


def taint_sink(o):
    os.system(str(o))  


if __name__ == "__main__":
    taint_src = (1, 2, 3, 4)  # 示例输入，可以是一个元组
    conditional_match_008_F(taint_src)
