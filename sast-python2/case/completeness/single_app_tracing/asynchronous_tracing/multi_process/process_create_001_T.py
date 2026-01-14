# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多进程
# scene introduction = Process直接创建
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_process/process_create_001_T
# date = 2026-01-07 06:14:06
# evaluation information end
import os
import multiprocessing


def process_create_001_T(taint_src):
    result = taint_src

    def worker():
        global result
        # 场景特点：进程直接访问污染源参数
        result = "safe_value"

    # 场景特点：直接创建Process对象并传递参数
    process = multiprocessing.Process(target=worker)
    process.start()

    # 场景特点：污染源通过进程参数传递并最终到达sink
    taint_sink(result)


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    process_create_001_T(taint_src)
