# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = 锁
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/lock_create_001_T
# date = 2026-01-07 06:26:35
# evaluation information end
import os
import threading
import time


def lock_create_001_T(taint_src):
    result = [""]
    lock = threading.Lock()

    def worker1():
        # 等待 worker2 同步锁
        time.sleep(0.1)
        with lock:
            result[0] = taint_src

    def worker2():
        with lock:
            # lock 锁住等 0.2s 再释放
            time.sleep(0.2)
            result[0] = "safe_value"

    thread1 = threading.Thread(target=worker1)
    thread2 = threading.Thread(target=worker2)

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    taint_sink(result[0])


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    lock_create_001_T(taint_src)
