# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->延迟执行异步
# scene introduction = 线程延迟执行
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/delayed_execution_async/thread_delay_direct_001_T
# date = 2026-01-08 02:38:35
# evaluation information end
import os
import time
import threading

def thread_delay_direct_001_T(taint_src):
    result = None
    
    def delayed_thread():
        # 场景特点：在线程中使用sleep实现延迟
        time.sleep(0.1)
        global result
        result = taint_src
        taint_sink(result)
    
    # 场景特点：创建并启动延迟执行线程
    thread = threading.Thread(target=delayed_thread)
    thread.start()
    thread.join()

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    thread_delay_direct_001_T(taint_src)