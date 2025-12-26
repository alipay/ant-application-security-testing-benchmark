# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->流敏感分析->异步执行
# scene introduction = 异步执行-await
# level = 4
# bind_url = accuracy/flow_sensitive/asynchronous/asyncio_await_001_T
# evaluation information end
import os
import asyncio


async def asyncio_await_001_T(taint_src):
    result = "safe_value"

    async def process():
        await asyncio.sleep(0.01)
        return taint_src

    result = await process()
    taint_sink(result)


def taint_sink(o):
    os.system(o)


# 启动事件循环（Python 3.7+）
if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(asyncio_await_001_T(taint_src))
