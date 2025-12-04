// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 字符串类型
// level = 2
// date = 2025-11-28 16:16:41
// bind_url = completeness/single_app_tracing/datatype/primitives/primitives_string_001_T/primitives_string_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func primitives_string_001_T(__taint_src string) {
	// 场景特点：字符串类型直接传递
	__taint_sink(__taint_src)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	primitives_string_001_T(__taint_src)
}
