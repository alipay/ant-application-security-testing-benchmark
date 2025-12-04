// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 二元运算->取模
// level = 2
// date = 2025-11-20 15:14:45
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_009_T/binary_009_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func binary_009_T(__taint_src int) {
	result := __taint_src % 2
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := 10
	binary_009_T(__taint_src)
}
