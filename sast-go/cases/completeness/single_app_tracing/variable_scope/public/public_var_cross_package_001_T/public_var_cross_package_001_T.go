// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->public变量
// scene introduction = Public变量跨包访问
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/public/public_var_cross_package_001_T/public_var_cross_package_001_T
// date = 2025-12-01 15:25:25
// evaluation information end

package main

import (
	"ant-application-security-testing-benchmark/sast-go/cases/completeness/single_app_tracing/variable_scope/public/public_var_cross_package_001_T/mypackage"
	"fmt"
	"os/exec"
)

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	// 场景特点：在不同包中为public变量赋值
	mypackage.SetPublicVar(__taint_src)
	// 场景特点：在主包中访问不同包的public变量
	__taint_sink(mypackage.PublicVar)
}
