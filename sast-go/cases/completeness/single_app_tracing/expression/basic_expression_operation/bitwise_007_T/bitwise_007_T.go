// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 位操作->按位取反
// level = 2
// date = 2025-11-20 15:05:13
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/bitwise_007_T/bitwise_007_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func bitwise_007_T(__taint_src int) {
	result := ^__taint_src
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := 10
	bitwise_007_T(__taint_src)
}
