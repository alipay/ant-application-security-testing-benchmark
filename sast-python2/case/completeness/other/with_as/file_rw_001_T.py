# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->其他->资源管理
# scene introduction = 文件操作->安全读写
# level = 2+
# bind_url = completeness/other/with_as/file_rw_001_T
# evaluation information end


from __future__ import with_statement
import os
from io import open

def file_rw_001_T(taint_src):
    
    with open(u'example.txt', u'w') as file:
        file.write(taint_src)
    
    with open(u'example.txt', u'r') as file:
        read_content = file.read()
        taint_sink(read_content)


def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    file_rw_001_T(taint_src)
