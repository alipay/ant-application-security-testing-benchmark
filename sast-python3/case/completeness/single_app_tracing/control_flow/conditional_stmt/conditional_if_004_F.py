# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
# scene introduction = elseif
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_if_004_F
# evaluation information end
import os


def conditional_if_004_F(taint_src):
    if False:
        pass
    elif True:
        taint_sink("safe_value")
    else:
        pass


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_if_004_F(taint_src)
