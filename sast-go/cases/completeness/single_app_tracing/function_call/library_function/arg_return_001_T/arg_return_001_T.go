package main
import (
	"os/exec"
	"fmt"
)


// evaluation information start
// real case = true
// evaluation item =完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = 从参数传播到返回值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/library_function/arg_return_001_T/arg_return_001_T
// evaluation information end

func arg_return_001_T(__taint_src string) {
	taintedData := __taint_src
	var sList []string
	result := appendTaint(sList, taintedData)
	__taint_sink(result)
}

func appendTaint(taintSrc []string, newTaint string) []string {
	b := append(taintSrc, newTaint) // 从参数传播到返回值
	return b
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    arg_return_001_T(__taint_src)
}