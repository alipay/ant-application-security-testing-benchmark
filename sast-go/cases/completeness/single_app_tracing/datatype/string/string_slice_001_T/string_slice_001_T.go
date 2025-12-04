// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串切片
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_slice_001_T/string_slice_001_T
// date = 2025-12-01 14:42:05
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func string_slice_001_T(__taint_src string) {
	result := __taint_src
	__taint_sink(result[0:5])
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	string_slice_001_T(__taint_src)
}
