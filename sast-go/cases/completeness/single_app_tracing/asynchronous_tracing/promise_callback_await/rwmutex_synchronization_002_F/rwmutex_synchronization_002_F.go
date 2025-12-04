// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = 读写锁
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/rwmutex_synchronization_002_F/rwmutex_synchronization_002_F
// date = 2025-11-28 10:36:30
// evaluation information end

package main

import (
	"os/exec"
	"sync"
	"time"
)

func rwmutex_synchronization_002_F(__taint_src string) {
	var rwMu sync.RWMutex
	var result string
	var wg sync.WaitGroup

	wg.Add(2)
	// 场景特点：使用读写锁保护数据，写操作加写锁
	go func() {
		defer wg.Done()
		rwMu.Lock()
		result = __taint_src
		rwMu.Unlock()
	}()

	go func() {
		defer wg.Done()
		time.Sleep(1 * time.Second)
		rwMu.RLock()
		_ = result
		__taint_sink("safe_value")
		rwMu.RUnlock()
	}()

	wg.Wait()
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	rwmutex_synchronization_002_F(__taint_src)
}
