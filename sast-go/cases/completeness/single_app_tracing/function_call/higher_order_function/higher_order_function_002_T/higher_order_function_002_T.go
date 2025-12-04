
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 一阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_002_T/higher_order_function_002_T
// evaluation information end

package main
import "os/exec"

func higher_order_function_002_T(__taint_src string) {
	f := func(a string, b string) func() string {
		c := "_"
		return func() string {
			return a + b + c
		}
	}

	__taint_sink(f(__taint_src, "_")())
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    higher_order_function_002_T(__taint_src)
}