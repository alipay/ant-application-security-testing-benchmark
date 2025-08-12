
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 匿名函数作参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_008_T/higher_order_function_008_T
// evaluation information end

package main
import "os/exec"

func higher_order_function_008_T(__taint_src string) {
	result := higher_order_function_008_Function(func(a string, b string) string {
		return a + b
	}, __taint_src)
	__taint_sink(result)
}

func higher_order_function_008_Function(callback func(a string, b string) string, src string) string {
	return callback(src, "_")
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    higher_order_function_008_T(__taint_src)
}