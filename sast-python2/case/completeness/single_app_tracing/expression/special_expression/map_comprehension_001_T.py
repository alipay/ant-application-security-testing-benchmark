# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = 字典推导式
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/map_comprehension_001_T
# evaluation information end

import os

def map_comprehension_001_T(taint_src):
    def process():       
        # 使用字典推导式创建map，其中包含污染数据作为部分键或值
        result = dict((i, taint_src) for i in xrange(1)) # 简化示例，只循环一次以展示概念
        taint_sink(result[0])

    process()  

def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"  
    map_comprehension_001_T(taint_src)
