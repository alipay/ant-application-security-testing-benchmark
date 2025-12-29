package main
import "os/exec"

import "strconv"


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = 从参数传播到返回值
// level = 2
// bind_url = completeness/single_app_tracing/function_call/library_function/arg_return_004_F/arg_return_004_F
// evaluation information end

func arg_return_004_F(__taint_src int) {
	taintedData := __taint_src
	taintedData = 1
	result := itoaTaint(taintedData)
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func itoaTaint(taintSrc int) string {
	b := strconv.Itoa(taintSrc)
	return b
}

func main() {
    __taint_src := 3
    arg_return_004_F(__taint_src)
}