# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = 进程同步
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/process_join_002_F
# date = 2026-01-07 06:30:09
# evaluation information end
import os
import multiprocessing


def process_join_002_F(taint_src):
    result = multiprocessing.Manager().list([taint_src])

    def worker(list, data):
        # 场景特点：进程同步时传递安全值而非污染源
        list[0] = "safe_value"

    process = multiprocessing.Process(target=worker, args=(result, taint_src))
    process.start()

    process.join()
    taint_sink(result[0])


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    process_join_002_F(taint_src)
