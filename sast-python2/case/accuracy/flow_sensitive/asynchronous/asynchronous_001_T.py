# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->流敏感分析->异步执行
# scene introduction = asynchronous
# level = 4
# bind_url = accuracy/flow_sensitive/asynchronous/asynchronous_001_T
# date = 2026-01-07 06:02:28
# evaluation information end
import os
import threading
import time

def asynchronous_001_T(taint_src):
    result = taint_src
    
    def worker():
        global result
        time.sleep(0.1)
        result = "safe_value"
    
    thread = threading.Thread(target=worker)
    thread.start()
    
    taint_sink(result)

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    asynchronous_001_T(taint_src)