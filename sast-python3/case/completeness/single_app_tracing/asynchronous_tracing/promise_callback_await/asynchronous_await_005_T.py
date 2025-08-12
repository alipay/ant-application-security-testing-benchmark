# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = Semaphore并发控制
# level = 2+
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_await_005_T
# evaluation information end

import os
import asyncio
semaphore = asyncio.Semaphore(2)  # 允许最多2个并发任务

async def asynchronous_await_005_T(taint_src):
    tasks = [download_task(semaphore, taint_src) for _ in range(6)]
    await asyncio.gather(*tasks)
   


async def download_task(sem, taint):
    async with sem:
        await asyncio.sleep(2)
        taint_sink(taint)  # 确保仅2个任务同时运行
        return taint  


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(asynchronous_await_005_T(taint_src))

