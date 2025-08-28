
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 返回值传递
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_value_passing_002_T/return_value_passing_002_T
// evaluation information end

package main
import "os/exec"

func return_value_passing_002_T(__taint_src string) {
	data := process(__taint_src)
	__taint_sink(data)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func process(__taint_src string) interface{} {
	return __taint_src
}

func main() {
    __taint_src := "taint_src_value"
    return_value_passing_002_T(__taint_src)
}