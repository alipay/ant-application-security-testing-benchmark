# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多进程
# scene introduction = Process直接创建
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_process/process_create_002_F
# date = 2026-01-07 06:14:06
# evaluation information end
import os
import multiprocessing


def process_create_002_F(taint_src):
    result = "safe_value"

    def worker():
        global result
        # 场景特点：进程使用安全值而非污染源
        result = taint_src

    # 场景特点：直接创建Process对象但不传递污染源
    process = multiprocessing.Process(target=worker)
    process.start()

    # 场景特点：安全值到达sink，污染源未传递
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    process_create_002_F(taint_src)
