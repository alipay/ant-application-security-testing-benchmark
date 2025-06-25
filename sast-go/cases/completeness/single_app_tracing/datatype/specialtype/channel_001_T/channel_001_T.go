
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->通道
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/specialtype/channel_001_T/channel_001_T
// evaluation information end

package channel_001_T

func channel_001_T(__taint_src string) {
	var ch chan string
	ch = make(chan string, 1)
	ch <- __taint_src
	c := <-ch
	__taint_sink(c)
}
func __taint_sink(o interface{}) {
}
