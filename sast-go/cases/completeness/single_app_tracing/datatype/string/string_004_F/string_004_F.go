// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串拼接
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_004_F/string_004_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func string_004_F(__taint_src string) {
	object := "abc" + " "
	__taint_sink(object)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}
func main() {
	__taint_src := "taint_src_value"
	string_004_F(__taint_src)
}
