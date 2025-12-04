// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
// scene introduction = 字符串到数值转换
// level = 2
// date = 2025-11-20 19:27:07
// bind_url = completeness/single_app_tracing/expression/type_cast/type_cast_006_F/type_cast_006_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
	"strconv"
)

func type_cast_006_F(__taint_src string) {
	// 场景特点：字符串转换后重新赋值
	result, _ := strconv.Atoi(__taint_src)
	result = 456
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "123"
	type_cast_006_F(__taint_src)
}
