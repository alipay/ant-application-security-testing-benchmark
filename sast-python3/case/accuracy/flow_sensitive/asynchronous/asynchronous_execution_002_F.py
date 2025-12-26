# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->流敏感分析->异步执行
# scene introduction = 异步执行
# level = 4
# bind_url = accuracy/flow_sensitive/asynchronous/asynchronous_execution_002_F
# evaluation information end
import os
import asyncio


async def asynchronous_execution_002_F(taint_src):
    result = 'safe_value'

    async def process():
        await asyncio.sleep(0.1)
        result = taint_src

    asyncio.create_task(process())

    taint_sink(result)  # 通过分支保留污点


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(asynchronous_execution_002_F(taint_src))
