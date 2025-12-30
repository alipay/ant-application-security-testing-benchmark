// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->public变量
// scene introduction = Public变量跨包访问
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/public/public_var_cross_package_002_F/public_var_cross_package_002_F
// date = 2025-12-01 15:25:25
// evaluation information end

package main

import (
	"ant-application-security-testing-benchmark/sast-go/cases/completeness/single_app_tracing/variable_scope/public/public_var_cross_package_002_F/mypackage"
	"fmt"
	"os/exec"
)

func public_var_cross_package_002_F(__taint_src) {
	// 场景特点：在不同包中为public变量赋值
	mypackage.SetPublicVar(__taint_src)
	// 场景特点：在主包中访问不同包的public变量，但污点数据未传播到该变量
	__taint_sink(mypackage.PublicVar)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
    public_var_cross_package_002_F(__taint_src)
}
