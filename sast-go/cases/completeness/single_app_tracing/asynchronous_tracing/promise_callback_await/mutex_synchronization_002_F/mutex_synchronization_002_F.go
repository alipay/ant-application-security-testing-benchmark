// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = 互斥锁
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/mutex_synchronization_002_F/mutex_synchronization_002_F
// date = 2025-11-28 10:36:30
// evaluation information end

package main

import (
	"os/exec"
	"sync"
	"time"
)

func mutex_synchronization_002_F(__taint_src string) {
	var mu sync.Mutex
	var wg sync.WaitGroup
	var result string = __taint_src

	wg.Add(2)
	// 场景特点：使用互斥锁保护临界区，确保数据一致性
	go func() {
		defer wg.Done()
		mu.Lock()
		time.Sleep(2 * time.Second)
		result = "1"
		mu.Unlock()
	}()

	go func() {
		defer wg.Done()
		time.Sleep(1 * time.Second)
		mu.Lock()
		result = result + "2"
		mu.Unlock()
	}()

	wg.Wait()

	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	mutex_synchronization_002_F(__taint_src)
}
