
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 具名返回值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/named_return_004_T/named_return_004_T
// evaluation information end

package main
import "os/exec"

func named_return_004_T(__taint_src interface{}) {
	a := "_"

	ret := processData(__taint_src, a)
	__taint_sink(ret)
}

func processData(s interface{}, i interface{}) (ret interface{}) {
	ret = s
	return
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    named_return_004_T(__taint_src)
}