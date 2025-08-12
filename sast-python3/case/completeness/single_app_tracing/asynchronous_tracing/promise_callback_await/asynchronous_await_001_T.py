# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = 异步-async/await
# level = 2+
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_await_001_T
# evaluation information end

import os
import asyncio


async def asynchronous_await_001_T(taint_src):
    data = await process(taint_src)
    taint_sink(data)


async def process(taint_src):
    await asyncio.sleep(0.01)
    return taint_src


def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    #异步编程的扁平化
    asyncio.run(asynchronous_await_001_T(taint_src))

