
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = select
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/asynchronous_select_001_T/asynchronous_select_001_T
// evaluation information end

package main
import "os/exec"

import (
	"fmt"
	"time"
)

func asynchronous_select_001_T(__taint_src string) {
	ch := make(chan string)

	go func() {
		ch <- __taint_src
	}()

	select {
	case result := <-ch:
		__taint_sink(result)
	case <-time.After(50 * time.Millisecond):
		fmt.Println("Timeout waiting for data")
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    asynchronous_select_001_T(__taint_src)
}