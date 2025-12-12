// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = 原子操作
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/atomic_synchronization_001_T/atomic_synchronization_001_T
// date = 2025-11-28 10:36:30
// evaluation information end

package main

import (
	"os/exec"
	"sync"
	"sync/atomic"
)

func atomic_synchronization_001_T(__taint_src string) {
	var sharedData atomic.Value
	var done int32
	var wg sync.WaitGroup

	// 场景特点：使用原子操作在goroutine间安全地传递数据
	wg.Add(1)
	go func() {
		defer wg.Done()
		sharedData.Store(__taint_src)
		atomic.StoreInt32(&done, 1)
	}()

	// 等待写入操作完成
	wg.Wait()

	// 现在进行读取操作
	wg.Add(1)
	go func() {
		defer wg.Done()
		for atomic.LoadInt32(&done) == 0 {
			// 等待数据准备完成
		}
		data := sharedData.Load().(string)
		__taint_sink(data)
	}()

	// 等待读取操作完成
	wg.Wait()
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	atomic_synchronization_001_T(__taint_src)
}
