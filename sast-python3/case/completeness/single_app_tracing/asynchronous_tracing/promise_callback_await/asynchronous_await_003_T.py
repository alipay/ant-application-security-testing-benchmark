# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
# scene introduction = Lock保护共享状态
# level = 2
# bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_await_003_T
# evaluation information end

import os
import asyncio  # 导入asyncio库，用于异步编程
lock = asyncio.Lock()  # 创建一个异步锁对象，用于控制对共享资源的访问
shared_counter = "_"  # 定义一个全局变量作为共享计数器，初始值设为"_"

# 定义一个异步函数，接收一个参数__taint_src，该函数将启动多个并发任务
async def asynchronous_await_003_T(taint_src):
    tasks = [  # 创建一个包含三个任务的列表，每个任务都调用increment_counter函数并传入__taint_src
        increment_counter(taint_src),
        increment_counter(taint_src),
        increment_counter(taint_src),
    ]
    await asyncio.gather(*tasks)  # 并发执行所有任务，并等待它们全部完成
    taint_sink(shared_counter)  # 所有任务完成后，调用taint_sink函数处理共享计数器的结果

# 定义一个异步函数，用于模拟对共享计数器的递增操作
async def increment_counter(taint):
    global shared_counter  # 声明使用全局变量shared_counter
    async with lock:  # 使用异步上下文管理器获取锁，确保同一时刻只有一个任务能执行下面的代码块
        current = shared_counter  # 获取当前共享计数器的值
        await asyncio.sleep(0.01)  # 模拟耗时操作（例如I/O操作），让出控制权给其他协程
        shared_counter = current + taint  # 更新共享计数器的值

def taint_sink(o):  # 定义一个函数，用于打印传入的参数o
    os.system(o)

if __name__ == "__main__":  # 确保当脚本被直接运行时，以下代码块被执行
    taint_src = "taint_src_value"
    asyncio.run(asynchronous_await_003_T(taint_src))  # 运行asynchronous_await_003_T函数，并传入字符串__taint_src

