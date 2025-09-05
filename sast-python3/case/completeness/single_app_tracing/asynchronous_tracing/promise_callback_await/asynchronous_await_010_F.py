# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = 协程类型注解
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_await_010_F
# evaluation information end

import os
import asyncio
from typing import Coroutine


async def asynchronous_await_010_F(taint_src):
    async def process(taint_src) -> str:
          await asyncio.sleep(0.01)
          return "_"

    # 使用 Coroutine 类型注解来表示异步函数类型注解
    coro: Coroutine[None, None, str] = process(taint_src)
    data = await coro
    taint_sink(data)



def taint_sink(o):
    os.system(o)


# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(asynchronous_await_010_F(taint_src))





