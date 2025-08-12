
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 三阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_005_F/higher_order_function_005_F
// evaluation information end

package main
import "os/exec"

func higher_order_function_005_F(__taint_src interface{}) {
	__taint_sink(f(g, u, __taint_src, "_")())
}

func f(g func(func(string, string, string) func() string, string, string) func() string, u func(string, string, string) func() string, a interface{}, b string) func() string {
	return g(u, a.(string), b)
}

func g(u func(string, string, string) func() string, a string, b string) func() string {
	c := "_"
	return u(a, b, c)
}

func u(a string, b string, c string) func() string {
	return func() string {
		return "safe" + c
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    higher_order_function_005_F(__taint_src)
}