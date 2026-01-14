# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = 线程同步
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/thread_join_001_T
# date = 2026-01-07 06:30:09
# evaluation information end
import os
import threading


def thread_join_001_T(taint_src):
    result = ["safe_value"]

    # 场景特点：创建线程并传递污染源
    def worker():
        # 场景特点：线程同步时传递污染源
        result[0] = taint_src

    thread = threading.Thread(target=worker)
    thread.start()

    thread.join()
    taint_sink(result[0])


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    thread_join_001_T(taint_src)
