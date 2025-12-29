// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 关系操作->大于等于
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_010_F/relation_010_F
// date = 2025-12-26 15:03:00
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func relation_010_F(__taint_src string) {
	// 场景特点：关系操作->大于等于
	var a int = 10
	var b int = 20
	var result bool = (a >= b) // 大于等于操作，结果为false
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
	relation_010_F(__taint_src)
}
