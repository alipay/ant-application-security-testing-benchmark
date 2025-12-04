// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = 条件变量
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/cond_synchronization_002_F/cond_synchronization_002_F
// date = 2025-11-28 10:36:30
// evaluation information end

package main

import (
	"os/exec"
	"sync"
)

func cond_synchronization_002_F(__taint_src string) {
	var mu sync.Mutex
	cond := sync.NewCond(&mu)
	var result string = ""
	var wg sync.WaitGroup

	wg.Add(1)
	// 启动一个goroutine来通知条件变量
	go func() {
		defer wg.Done()
		// 场景特点：使用条件变量等待和通知机制同步数据
		mu.Lock()
		for result == "" {
			cond.Wait()
		}
		__taint_sink(result)
		mu.Unlock()
	}()

	mu.Lock()
	result = "safe_value"
	cond.Signal()
	mu.Unlock()

	wg.Wait()
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	cond_synchronization_002_F(__taint_src)
}
