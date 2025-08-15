
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 结构体
// level = 2
// bind_url = completeness/single_app_tracing/datatype/struct/struct_002_F/struct_002_F
// evaluation information end

package main
import "os/exec"

type A struct {
	data string
}

func struct_002_F(__taint_src string) {
	p := A{
		data: "_",
	}
	__taint_sink(p)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    struct_002_F(__taint_src)
}