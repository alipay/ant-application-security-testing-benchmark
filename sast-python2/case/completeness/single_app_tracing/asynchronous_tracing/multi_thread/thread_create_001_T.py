# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
# scene introduction = Thread
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/thread_create_001_T
# date = 2026-01-07 06:02:28
# evaluation information end
import os
import threading
import time


def thread_create_001_T(taint_src):
    result = taint_src

    def worker():
        global result
        time.sleep(0.1)
        # 场景特点：线程使用安全值而非污染源
        result = "safe_value"

    # 场景特点：直接创建Thread对象但不传递污染源
    thread = threading.Thread(target=worker)
    thread.start()

    # 场景特点：安全值到达sink，污染源未传递
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    thread_create_001_T(taint_src)
