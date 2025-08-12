
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = 多Goroutine
// level = 2+
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/asynchronous_goroutine_channel_004_F/asynchronous_goroutine_channel_004_F
// evaluation information end

package main
import "os/exec"

import (
	"sync"
)

func asynchronous_goroutine_channel_004_F(__taint_src string) {
	var wg sync.WaitGroup
	ch := make(chan string)

	wg.Add(1)
	go func() {
		defer wg.Done()
		ch <- "_"
	}()

	wg.Add(1)
	go func() {
		defer wg.Done()
		message := <-ch
		__taint_sink(message)
	}()

	wg.Wait()
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    asynchronous_goroutine_channel_004_F(__taint_src)
}