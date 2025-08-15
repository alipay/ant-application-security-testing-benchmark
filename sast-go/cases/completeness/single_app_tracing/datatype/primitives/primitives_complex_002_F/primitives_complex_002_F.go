
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 复数类型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/primitives_complex_002_F/primitives_complex_002_F
// evaluation information end

package main
import "os/exec"

func primitives_complex_002_F(__taint_src complex64) {
	var sani complex64 = __taint_src
	sani = complex(1, 2)
	__taint_sink(sani)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    primitives_complex_002_F(__taint_src)
}