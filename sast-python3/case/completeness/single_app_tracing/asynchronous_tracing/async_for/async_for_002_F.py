# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->延迟执行异步
# scene introduction = 异步循环
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/async_for/async_for_002_F
# evaluation information end



import os
import asyncio

def async_for_002_F(taint_src):
    async def async_generator():
        yield taint_src
        yield "suffix"

    async def process():
        async for item in async_generator():  # 使用 AsyncFor 进行异步迭代
            taint_sink("_")

    asyncio.run(process())

def taint_sink(o):
    os.system(str(o))

if __name__ == "__main__":
    taint_src = "taint_src_value"
    async_for_002_F(taint_src)
