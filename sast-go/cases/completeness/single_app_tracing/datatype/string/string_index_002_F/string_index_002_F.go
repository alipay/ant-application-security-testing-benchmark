// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串索引访问
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_index_002_F/string_index_002_F
// date = 2025-12-01 14:42:05
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func string_index_002_F(__taint_src string) {
	// 场景特点：通过索引访问字符串中的字符，但污点数据未传播到该位置
	__taint_sink("_"[0])
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	string_index_002_F(__taint_src)
}
