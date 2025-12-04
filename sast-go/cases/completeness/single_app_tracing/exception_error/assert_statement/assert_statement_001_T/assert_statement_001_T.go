// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->断言
// scene introduction = 类型断言
// level = 2
// bind_url = completeness/single_app_tracing/exception_error/assert_statement/assert_statement_001_T/assert_statement_001_T
// evaluation information end

package main

import (
	"os/exec"
)

func assert_statement_001_T(__taint_src interface{}) {
	// 场景特点：对接口变量进行正确的类型断言，成功获取值
	str, ok := __taint_src.(string)
	if !ok {
		str = "safe_value"
	}

	__taint_sink(str)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	assert_statement_001_T(__taint_src)
}
