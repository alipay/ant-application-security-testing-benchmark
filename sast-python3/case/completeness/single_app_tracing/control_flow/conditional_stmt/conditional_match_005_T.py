# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
# scene introduction = match_as
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_match_005_T
# evaluation information end

import os


def conditional_match_005_T(taint_src):
    value = taint_src
    match value:
        case str() as pair:
            taint_sink(pair)
        case _ as default:
            taint_sink(default)


def taint_sink(o):
    os.system(o) 


if __name__ == "__main__":
    taint_src = "taint_src_value"  # 示例输入，可以是列表、字典或任何其他类型
    conditional_match_005_T(taint_src)
