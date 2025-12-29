# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->其他->exec
# scene introduction = 动态执行代码
# level = 2
# bind_url = completeness/other/exec/exec_001_T
# evaluation information end


import os

def exec_001_T(taint_src):
    result = u"aaa"
    # 使用exec执行代码字符串，修改result的值
    exec("result = " + taint_src)
    taint_sink(result)

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    exec_001_T(taint_src)

