# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = Condition条件同步
# level = 2+
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_await_007_T
# evaluation information end

import os
import asyncio
condition = asyncio.Condition()
shared_data = None
ready_for_production = False  # 新增状态变量，用于标记消费者是否准备好接收通知

async def asynchronous_await_007_T(taint_src):
    await asyncio.gather(
        producer(condition, taint_src),
        consumer(condition),
    )


async def producer(cond, taint):
    async with cond:
        # 等待直到消费者准备好接收通知
        while not ready_for_production:
            await cond.wait_for(lambda: ready_for_production)
        await asyncio.sleep(1)  # 模拟生产过程中的延迟
        global shared_data
        shared_data = taint 
        cond.notify_all()  # 通知所有等待的消费者


async def consumer(cond):
    async with cond:
        global ready_for_production
        ready_for_production = True  # 标记消费者已经准备好
        cond.notify_all()  # 通知生产者消费者已准备好
        try:
            await asyncio.wait_for(cond.wait(), timeout=5)  # 设置5秒超时
            taint_sink(shared_data)  
        except asyncio.TimeoutError:
            print("消费者等待超时")

def taint_sink(o):
    os.system(o)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(asynchronous_await_007_T(taint_src))

