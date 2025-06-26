# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->流敏感分析->常规顺序执行语句
# scene introduction = 赋值表达式
# level = 2
# bind_url = accuracy/flow_sensitive/normal_stmt/assign_expression_stmt_002_F
# evaluation information end
import os

def assign_expression_stmt_002_F(taint_src):
    result = ""
    taint_sink(result)
    result = taint_src

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    assign_expression_stmt_002_F(taint_src)

