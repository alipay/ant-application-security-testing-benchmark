# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
# scene introduction = Thread
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/thread_create_002_F
# date = 2026-01-07 06:02:28
# evaluation information end
import os
import threading
import time


def thread_create_002_F(taint_src):
    result = "safe_value"

    def worker():
        global result
        time.sleep(0.1)
        # 场景特点：线程直接访问污染源参数
        result = taint_src

    # 场景特点：直接创建Thread对象并传递参数
    thread = threading.Thread(target=worker)
    thread.start()

    # 场景特点：污染源通过线程传递并最终到达sink
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    thread_create_002_F(taint_src)
