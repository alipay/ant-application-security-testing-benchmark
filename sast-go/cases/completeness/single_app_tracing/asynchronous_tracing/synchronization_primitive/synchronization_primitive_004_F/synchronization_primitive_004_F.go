
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = 同步原语-'<-'
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/synchronization_primitive/synchronization_primitive_004_F/synchronization_primitive_004_F
// evaluation information end

package synchronization_primitive_004_F

func synchronization_primitive_004_F(__taint_src string) {
	ch := make(chan string, 2)
	ch <- "aaa"
	result, ok := <-ch
	if ok {
		//do nothing
	}
	__taint_sink(result)
}

func __taint_sink(o interface{}) {}
