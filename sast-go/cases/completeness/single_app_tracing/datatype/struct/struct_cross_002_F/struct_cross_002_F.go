
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 跨结构体访问变量
// level = 2
// bind_url = completeness/single_app_tracing/datatype/struct/struct_cross_002_F/struct_cross_002_F
// evaluation information end

package main
import "os/exec"

type A struct {
	data string
}

type B struct {
	data string
}

func struct_cross_002_F(__taint_src string) {
	pa := A{
		data: "_",
	}
	var pb B
	pb.data = pa.data
	__taint_sink(pb)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    struct_cross_002_F(__taint_src)
}