# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->流敏感分析->异步执行
# scene introduction = 异步执行->并发执行
# level = 4
# bind_url = accuracy/flow_sensitive/asynchronous/async_concurrent_002_F
# evaluation information end
import os
import asyncio


async def async_concurrent_002_F(taint_src):
    # 使用asyncio.gather并发执行多个异步函数
    results = await asyncio.gather(
        async_func1(taint_src),      # 污染函数
        async_func2("safe"),         # 安全函数
    )
    
    # 从并发结果中访问安全数据
    taint_sink(results[1])  # results[1] 是安全数据，不应检测到污染


async def async_func1(data):
    await asyncio.sleep(0.01)
    return data


async def async_func2(data):
    await asyncio.sleep(0.01)
    return data


def taint_sink(o):
    os.system(o)


async def main(taint_src):
    await async_concurrent_002_F(taint_src)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(main(taint_src))
