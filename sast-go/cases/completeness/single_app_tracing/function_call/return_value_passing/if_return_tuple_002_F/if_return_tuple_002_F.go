// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 条件返回tuple
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/if_return_tuple_002_F/if_return_tuple_002_F
// evaluation information end

package main
import "os/exec"

func callee(taint string) (string, string) {
	if taint == "" {
		return "a", "b"
	}
	return "hello", taint
}

func if_return_tuple_002_F(__taint_src string) {
	a,b := callee("")
	_ = a
	__taint_sink(b)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    if_return_tuple_002_F(__taint_src)
}