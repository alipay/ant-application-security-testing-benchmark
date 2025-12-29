// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 关系操作->大于等于
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_009_T/relation_009_T
// date = 2025-12-26 15:03:00
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func relation_009_T(__taint_src string) {
	// 场景特点：关系操作->大于等于
	var a int = 20
	var b int = 10
	var result bool = (a >= b) // 大于等于操作
	if result {
		__taint_sink(__taint_src)
	} else {
		__taint_sink("safe_value")
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	relation_009_T(__taint_src)
}
