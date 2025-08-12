# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->路径敏感分析->跳转语句
# scene introduction = return
# level = 4+
# bind_url = accuracy/path_sensitive/explicit_jump_control/return_002_F
# evaluation information end
import os


def return_002_F(taint_src):
    def process(arg1, arg2):
        if arg2 == u'some_condition':
            return              # 提前退出，跳过 taint_sink
        taint_sink(arg1)      # 未执行

    process(taint_src, u'some_condition')  # 调用时 arg2 固定为 'some_condition'


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    return_002_F(taint_src)

