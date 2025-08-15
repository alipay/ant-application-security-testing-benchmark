
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->指针
// scene introduction = 指针类型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/pointer/pointer_002_F/pointer_002_F
// evaluation information end

package main
import "os/exec"

func pointer_002_F(__taint_src string) {
	var sani string = "_"
	ps := &__taint_src
	ps = &sani
	__taint_sink(*ps)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    pointer_002_F(__taint_src)
}