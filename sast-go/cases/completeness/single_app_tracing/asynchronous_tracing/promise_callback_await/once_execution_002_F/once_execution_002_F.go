// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = Once单次执行
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/once_execution_002_F/once_execution_002_F
// date = 2025-11-28 10:36:30
// evaluation information end

package main

import (
	"os/exec"
	"sync"
)

var once sync.Once
var result string

func createOnce() {
	once.Do(func() {
		result = "safe_value"
	})
}

func once_execution_002_F(__taint_src string) {
	result = __taint_src
	// 场景特点：使用Once确保函数只执行一次，但污染源未传递到结果
	createOnce()
	createOnce()

	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	once_execution_002_F(__taint_src)
}
