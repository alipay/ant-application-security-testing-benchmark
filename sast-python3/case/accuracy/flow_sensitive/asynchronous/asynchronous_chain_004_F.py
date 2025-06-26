# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->流敏感分析->异步执行
# scene introduction = 异步函数链
# level = 4
# bind_url = accuracy/flow_sensitive/asynchronous/asynchronous_chain_004_F
# evaluation information end
import os
import asyncio

async def asynchronous_chain_004_F(taint_src):
    data1 = await async_chain_step1(taint_src)
    data2 = await async_chain_step2(data1)  # 所有分支均返回安全值
    taint_sink(data2)  # 未接收污点


async def async_chain_step1(taint_src):
    await asyncio.sleep(0.1)
    return taint_src

async def async_chain_step2(data):
    await asyncio.sleep(0.1)
    return "_"

def taint_sink(o):
    os.system(o)


async def main(taint_src):
    await asynchronous_chain_004_F(taint_src)

if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(main(taint_src))

