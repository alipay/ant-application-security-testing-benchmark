
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = Goroutine
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/asynchronous_goroutine_channel_002_F/asynchronous_goroutine_channel_002_F
// evaluation information end

package main
import "os/exec"

func asynchronous_goroutine_channel_002_F(__taint_src string) {
	ch := make(chan string)
	go func() {
		ch <- __taint_src
	}()
	<-ch
	__taint_sink("aa")
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    asynchronous_goroutine_channel_002_F(__taint_src)
}