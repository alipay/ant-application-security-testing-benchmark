# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 准确度->流敏感分析->异步执行
# scene introduction = 异步执行-await
# level = 4
# bind_url = accuracy/flow_sensitive/asynchronous/asyncio_await_002_F
# evaluation information end
import os
import asyncio

async def asyncio_await_002_F(taint_src):
    data = ''
    taint_sink(data)  # 此时 data 为空字符串，未被污染

    async def process():
        await asyncio.sleep(0.01)  
        return taint_src

    data = await process()  # 等待异步操作完成，但未将结果传递给 taint_sink


def taint_sink(o):
    os.system(o)


# 执行示例（需事件循环）
async def main(taint_src):
    await asyncio_await_002_F(taint_src)

# 启动事件循环（Python 3.7+）
if __name__ == "__main__":
    taint_src = "taint_src_value"
    asyncio.run(main(taint_src))

