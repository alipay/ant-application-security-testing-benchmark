// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->public变量
// scene introduction = Public变量赋值
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/public/public_var_assign_001_T/public_var_assign_001_T
// date = 2025-12-01 15:25:25
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

// Public变量（首字母大写）
var PublicVar string

func publicVarAssign_001_T(__taint_src string) {
	// 场景特点：为public变量赋值
	PublicVar = __taint_src
	__taint_sink(PublicVar)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	publicVarAssign_001_T(__taint_src)
}
