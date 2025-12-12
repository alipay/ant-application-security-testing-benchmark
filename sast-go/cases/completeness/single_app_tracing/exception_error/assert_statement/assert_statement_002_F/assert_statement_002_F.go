// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->断言
// scene introduction = 类型断言
// level = 2
// bind_url = completeness/single_app_tracing/exception_error/assert_statement/assert_statement_002_F/assert_statement_002_F
// evaluation information end

package main

import (
	"os/exec"
)

func assert_statement_002_F(__taint_src interface{}) {
	// 场景特点：对接口变量进行错误的类型断言，导致断言失败
	_, ok := __taint_src.(int)
	if !ok {
		_ = 0 // 断言失败时使用安全值
	}

	__taint_sink("safe_value")
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	assert_statement_002_F(__taint_src)
}
