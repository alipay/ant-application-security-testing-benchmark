
// evaluation information start
// real case = false
// evaluation item =完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = 多select选择
// level = 2+
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/asynchronous_multiple_select_002_F/asynchronous_multiple_select_002_F
// evaluation information end

package asynchronous_multiple_select_002_F

import (
	"fmt"
	"time"
)

func asynchronous_multiple_select_002_F(__taint_src string) {
	ch1 := make(chan string)
	ch2 := make(chan string)

	go func() {
		result := __taint_src
		result = "clean value"
		ch1 <- result
	}()

	go func() {
		result := __taint_src
		result = "clean value"
		ch2 <- result
	}()

	select {
	case result := <-ch1:
		__taint_sink(result)
	case result := <-ch2:
		__taint_sink(result)
	case <-time.After(60 * time.Millisecond):
		fmt.Println("Timeout waiting for data from both channels")
	}
	_ = __taint_src
}

func __taint_sink(o interface{}) {
}
