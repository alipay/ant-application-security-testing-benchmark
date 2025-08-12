package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = 从参数传播到参数
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/library_function/arg_arg_001_T/arg_arg_001_T
// evaluation information end

import "encoding/json"

func arg_arg_001_T(__taint_src string) {
	taintedData := __taint_src
	result, err := process(taintedData)
	_ = err
	__taint_sink(result)
}

func process(arg string) (map[string]interface{}, error) {
	var result map[string]interface{}
	err := json.Unmarshal([]byte(arg), &result)
	return result, err
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    arg_arg_001_T(__taint_src)
}