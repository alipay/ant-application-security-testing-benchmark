
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 命名返回值变量
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_008_T/anonymous_function_008_T
// evaluation information end

package main
import "os/exec"

func anonymous_function_008_T(__taint_src string) {
	t, _ := callee(__taint_src)
	__taint_sink(t)
}

func callee(s string) (taint string, mes string) {
	taint = s
	return taint, "测试在命名返回值的情况下，方法能否正确生成"
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
   __taint_src := "taint_src_value"
	 anonymous_function_008_T(__taint_src)
}