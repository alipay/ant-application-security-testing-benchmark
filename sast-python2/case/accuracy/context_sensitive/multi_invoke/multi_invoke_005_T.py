# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->上下文敏感分析->多次调用
# scene introduction = 类方法调用
# level = 2
# bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_005_T
# date = 2026-01-07 06:02:28
# evaluation information end
import os
import threading
import time

def multi_invoke_005_T(taint_src):
    class Base(object):
      def setValue(self, data):
        self.data = data
        return self
    
    base = Base()
    base.setValue("safe_value").setValue(taint_src)
    taint_sink(base.data)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    multi_invoke_005_T(taint_src)