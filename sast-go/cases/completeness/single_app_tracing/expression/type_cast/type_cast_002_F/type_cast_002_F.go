
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
// scene introduction = 显式类型转换
// level = 2
// bind_url = completeness/single_app_tracing/expression/type_cast/type_cast_002_F/type_cast_002_F
// evaluation information end

package main
import "os/exec"

func type_cast_002_F(__taint_src int) {
	result := float64(__taint_src)
	result = float64(0.2)
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    type_cast_002_F(__taint_src)
}