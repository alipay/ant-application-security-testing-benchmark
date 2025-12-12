// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串拼接
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_003_T/string_003_T
// evaluation information end

// 当memberAccess的object来自特殊expression，比如binaryExpression时

package main

import (
	"fmt"
	"os/exec"
)

func string_003_T(__taint_src string) {
	object := __taint_src + " "
	__taint_sink(object)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}
func main() {
	__taint_src := "taint_src_value"
	string_003_T(__taint_src)
}
