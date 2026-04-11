# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = 条件变量
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/condition_create_001_T
# evaluation information end

import os
import threading
import time


def condition_create_001_T(taint_src):
    result = [""]
    # 场景特点：创建条件变量对象并直接传递污染源
    condition = threading.Condition()

    def worker1():
        with condition:
            # 等待通知再写入
            condition.wait()
            result[0] = taint_src

    def worker2():
        # 等待 0.1s 看 worker1 是否在等通知
        time.sleep(0.1)
        with condition:
            result[0] = "safe_value"
            # 写入后通知 worker1
            condition.notify()

    thread1 = threading.Thread(target=worker1)
    thread2 = threading.Thread(target=worker2)

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    # 场景特点：条件变量对象作为参数传递污染源到sink
    taint_sink(result[0])


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    condition_create_001_T(taint_src)
