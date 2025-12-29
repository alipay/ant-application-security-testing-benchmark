
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = 多Goroutine
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/asynchronous_goroutine_channel_003_T/asynchronous_goroutine_channel_003_T
// evaluation information end

package main
import "os/exec"

import (
	"sync"
)

func asynchronous_goroutine_channel_003_T(__taint_src string) {
	var wg sync.WaitGroup
	ch := make(chan string)

	wg.Add(1)
	go func() {
		defer wg.Done()
		ch <- __taint_src
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
    asynchronous_goroutine_channel_003_T(__taint_src)
}