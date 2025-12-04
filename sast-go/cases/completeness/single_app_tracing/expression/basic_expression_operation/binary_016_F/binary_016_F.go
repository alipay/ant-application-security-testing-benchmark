// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 二元运算->乘等
// level = 2
// date = 2025-11-20 15:54:57
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_016_F/binary_016_F
// evaluation information end

package main

import "os/exec"

func binary_016_F(__taint_src int) {
	result := __taint_src
	result *= 1
	result = 20
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := 10
	binary_016_F(__taint_src)
}
