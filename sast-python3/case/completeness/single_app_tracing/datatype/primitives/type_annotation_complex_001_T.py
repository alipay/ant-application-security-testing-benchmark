# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
# scene introduction = 复数->类型注解
# level = 2
# bind_url = completeness/single_app_tracing/datatype/primitives/type_annotation_complex_001_T
# evaluation information end

import os
#一个复数由实部和虚部组成，3+2j是一个复数，它有3的实部和2的虚部。
def type_annotation_complex_001_T(taint_src):
   x: complex = taint_src
   a: complex = 3 + 4j
   sum = x + a  

   taint_sink(sum)

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src: complex = 1 + 2j
    type_annotation_complex_001_T(taint_src)
