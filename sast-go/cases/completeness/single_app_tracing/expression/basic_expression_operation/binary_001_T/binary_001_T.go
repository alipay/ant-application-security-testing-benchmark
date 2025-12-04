// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 二元运算->加
// level = 2
// date = 2025-11-20 15:05:13
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_001_T/binary_001_T
// evaluation information end

package main

import "os/exec"

func binary_001_T(__taint_src string) {
	result := __taint_src + "_"
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	binary_001_T(__taint_src)
}
