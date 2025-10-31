
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
	a := "abc"

	ret := processData(__taint_src, a)
	__taint_sink(ret)
}

func processData(s interface{}, i interface{}) (ret interface{}) {
	ret = "_"
	return s // 主要区别位于这里，在具名返回值的情况下 裸返回return默认返回ret。但uast4Go在处理具名返回值时存在bug，导致此处的return s被覆盖成return ret
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    named_return_004_T(__taint_src)
}