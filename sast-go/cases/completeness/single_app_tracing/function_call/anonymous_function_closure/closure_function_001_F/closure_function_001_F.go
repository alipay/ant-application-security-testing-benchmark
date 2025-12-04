
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 一阶闭包
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_001_F/closure_function_001_F
// evaluation information end

package main
import "os/exec"

func closure_function_001_F(__taint_src interface{}) {
	closure := func() {
		safe := __taint_src
		safe = "safe"
		__taint_sink(safe)
	}

	closure()
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    closure_function_001_F(__taint_src)
}