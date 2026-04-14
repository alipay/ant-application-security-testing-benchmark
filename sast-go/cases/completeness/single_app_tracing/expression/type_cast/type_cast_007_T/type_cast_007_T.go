// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
// scene introduction = 指针类型转换
// level = 2
// date = 2025-11-20 19:27:07
// bind_url = completeness/single_app_tracing/expression/type_cast/type_cast_007_T/type_cast_007_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func type_cast_007_T(__taint_src *string) {
	// 场景特点：指针类型转换为接口类型
	var result interface{} = __taint_src
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	type_cast_007_T(&__taint_src)
}
