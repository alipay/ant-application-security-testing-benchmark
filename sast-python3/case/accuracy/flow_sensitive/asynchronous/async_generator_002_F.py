# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->流敏感分析->异步执行
# scene introduction = 异步执行->生成器
# level = 4
# bind_url = accuracy/flow_sensitive/asynchronous/async_generator_002_F
# evaluation information end
import os
import asyncio


async def async_generator_002_F(taint_src):
    # 异步生成器函数 - 直接yield安全数据
    async def generate_data():
        yield "safe_data"    # 直接yield安全数据
        return  # 确保生成器结束，避免StopAsyncIteration
    
    # 获取异步生成器对象
    gen = generate_data()
    
    # 直接await获取第一个yield的值（最纯粹的异步生成器测试）
    first_item = await gen.__anext__()
    taint_sink(first_item)  # 直接处理生成器yield的安全数据


def taint_sink(o):
    os.system(o)


async def main(taint_src):
    await async_generator_002_F(taint_src)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(main(taint_src))
