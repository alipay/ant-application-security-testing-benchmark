package main


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = 从参数传播到参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/library_function/arg_arg_002_F/arg_arg_002_F
// evaluation information end

import (
	"os/exec"
	"encoding/json"
	"fmt"
)

func arg_arg_002_F(__taint_src string) {
	taintedData := __taint_src
	result, err := process(taintedData)
	_ = result
	__taint_sink(err)
}

// 使用库函数处理数据，并将数据流传递到返回值
func process(arg string) (map[string]interface{}, error) {
	var result map[string]interface{}
	err := json.Unmarshal([]byte(arg), &result)
	return result, err
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
	__taint_src := "{\"key\": \"taint_src_value\"}" 
    arg_arg_002_F(__taint_src)
}