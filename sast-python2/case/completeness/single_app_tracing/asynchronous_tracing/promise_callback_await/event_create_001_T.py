# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = 事件
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/event_create_001_T
# date = 2026-01-07 06:26:35
# evaluation information end
import os
import threading
import time


def event_create_001_T(taint_src):
    result = ["safe_value"]
    event = threading.Event()

    def worker(event):
        time.sleep(0.1)
        result[0] = taint_src
        event.set()

    thread = threading.Thread(target=worker, args=(event,))
    thread.start()

    event.wait()

    taint_sink(result[0])


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    event_create_001_T(taint_src)
