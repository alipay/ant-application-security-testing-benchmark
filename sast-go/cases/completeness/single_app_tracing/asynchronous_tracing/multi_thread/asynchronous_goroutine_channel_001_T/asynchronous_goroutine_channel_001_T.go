
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = Goroutine
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/asynchronous_goroutine_channel_001_T/asynchronous_goroutine_channel_001_T
// evaluation information end

package asynchronous_goroutine_channel_001_T

func asynchronous_goroutine_channel_001_T(__taint_src string) {
	ch := make(chan string)
	go func() {
		ch <- __taint_src
	}()
	__taint_sink(<-ch)
}

func __taint_sink(o interface{}) {
}
