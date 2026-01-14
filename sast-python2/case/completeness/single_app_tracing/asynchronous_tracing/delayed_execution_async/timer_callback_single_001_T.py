# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->延迟执行异步
# scene introduction = 定时器回调
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/delayed_execution_async/timer_callback_single_001_T
# date = 2026-01-08 02:38:35
# evaluation information end
import os
import threading

def timer_callback_single_001_T(taint_src):
    result = None
    
    def delayed_task():
        # 场景特点：定时器回调函数中直接使用污染源
        global result
        result = taint_src
        taint_sink(result)
    
    # 场景特点：设置单次定时器延迟执行
    timer = threading.Timer(1.0, delayed_task)
    timer.start()
    timer.join()

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    timer_callback_single_001_T(taint_src)