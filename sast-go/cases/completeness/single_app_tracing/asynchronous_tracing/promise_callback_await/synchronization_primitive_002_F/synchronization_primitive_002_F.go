
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = 同步原语-'<-'
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/synchronization_primitive_002_F/synchronization_primitive_002_F
// evaluation information end

package main
import "os/exec"

func synchronization_primitive_002_F(__taint_src string) {
	ch := make(chan string, 2)
	ch <- __taint_src
	result := <-ch
	result = "aa"
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    synchronization_primitive_002_F(__taint_src)
}