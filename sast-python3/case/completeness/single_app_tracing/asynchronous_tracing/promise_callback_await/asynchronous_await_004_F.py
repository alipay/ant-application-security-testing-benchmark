# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = Lock保护共享状态
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_await_004_F
# evaluation information end

import os
import asyncio
shared_counter = "_"

async def asynchronous_await_004_F(taint_src):
    tasks = [
        increment_counter(taint_src),
        increment_counter("b"),
        increment_counter("c"),
    ]
    await asyncio.gather(*tasks)
    taint_sink(shared_counter)  # 通过锁确保计数器正确累加


async def increment_counter(taint):
    global shared_counter
    current = shared_counter
    await asyncio.sleep(0.01)  # 模拟耗时操作
    shared_counter = current + taint


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(asynchronous_await_004_F(taint_src))

