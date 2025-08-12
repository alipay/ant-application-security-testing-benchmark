
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->通道
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/specialtype/channel_002_F/channel_002_F
// evaluation information end

package main
import "os/exec"

func channel_002_F(__taint_src string) {
	var ch chan string
	ch = make(chan string, 1)
	ch <- __taint_src
	c := <-ch
	c = "_"
	__taint_sink(c)
}
func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    channel_002_F(__taint_src)
}