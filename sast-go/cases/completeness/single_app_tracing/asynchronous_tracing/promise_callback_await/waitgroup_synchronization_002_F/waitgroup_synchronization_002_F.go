// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = WaitGroup
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/waitgroup_synchronization_002_F/waitgroup_synchronization_002_F
// date = 2025-11-28 10:36:30
// evaluation information end

package main

import (
	"os/exec"
	"sync"
)

func waitgroup_synchronization_002_F(__taint_src string) {
	var wg sync.WaitGroup
	var result string

	wg.Add(1)
	go func() {
		defer wg.Done()
		// 场景特点：使用WaitGroup等待goroutine完成，但污染源未传递到结果
		result = __taint_src
	}()

	wg.Wait()
	result = "safe_value" // 污染源被覆盖
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	waitgroup_synchronization_002_F(__taint_src)
}
